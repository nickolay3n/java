import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.OlderTerm;
import com.sun.mail.imap.YoungerTerm;

import javax.mail.*;
import javax.mail.search.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkerEml {

    public static String PathToSave = "c:/temp/";
    private static IMAPFolder trashfolder = null;

    public static Date[] get_archive_render( ) throws MessagingException {
        ////////получаем время из формы
        Main.myArchiveDate1.setText(Main.myArchiveDate1.getText().replaceAll(",","."));
        Main.myArchiveDate2.setText(Main.myArchiveDate2.getText().replaceAll(",","."));
        Main.myArchiveDate1.setText(Main.myArchiveDate1.getText().replaceAll("-","."));
        Main.myArchiveDate2.setText(Main.myArchiveDate2.getText().replaceAll("-","."));
        String Archdate1str = Main.myArchiveDate1.getText();
        String Archdate2str = Main.myArchiveDate2.getText();
        Date Archdate1,Archdate2  = new  Date();
        Archdate1=null;
        Archdate2=null;
        String regex;
        Matcher m;
        regex = "(\\d{2}.\\d{2}.\\d{4})";
        try {
            m = Pattern.compile(regex).matcher(Archdate1str);
            m.find();
            Archdate1 = new SimpleDateFormat("dd.MM.yyyy").parse(m.group(1));
        } catch (Exception e3) {}
        try {
            m = Pattern.compile(regex).matcher(Archdate2str);
            m.find();
            Archdate2 = new SimpleDateFormat("dd.MM.yyyy").parse(m.group(1));
        } catch (Exception e3) {}
        regex = "(\\d{4}.\\d{2}.\\d{2})";
        if (Archdate1 == null)
            try{
                m = Pattern.compile(regex).matcher(Archdate1str);
                m.find();
                Archdate1 = new SimpleDateFormat("yyyy.MM.dd").parse(m.group(1));
            } catch (Exception e3) {}
        if (Archdate2 == null)
            try{
                m = Pattern.compile(regex).matcher(Archdate2str);
                m.find();
                Archdate2 = new SimpleDateFormat("yyyy.MM.dd").parse(m.group(1));
            } catch (Exception e3) {}
        /*Date[] dateTorTurn = new Date[2];
        dateTorTurn[0]=Archdate1;
        dateTorTurn[1]=Archdate2;
        return dateTorTurn;*/
        return new Date[] {Archdate1, Archdate2};
    }
    public static void archive_render(Store store) throws MessagingException {
        if(Main.archiveBox.isSelected() == true ) {
            Main.mylog.append("обрабатывается архив\n");//отмечено а значит обрабатываем архив
            ////////////////////////////////////////////////////////

            Date[] dateArchive = new Date[2];
            dateArchive[0]=null;dateArchive[1]=null;
            dateArchive=get_archive_render();
            //Main.mylog.append("архив не пуст" + dateArchive[0] +'\n'+ dateArchive[1] +'\n');


            if ((Main.myArchiveDate1.getText().length() < 6 && Main.myArchiveDate2.getText().length() < 6) || (dateArchive[0]==null && dateArchive[1]==null))
            MOVETOINBOX:{//перемещение всех писем в inbox  ОБРАБАТЫВАЕМ СЛУЧАЙ ТОЛЬКО АРХИВ
                try {
                    trashfolder.open(Folder.READ_WRITE);
                    if (trashfolder.getMessageCount() != 0) {
                        trashfolder.moveMessages(trashfolder.getMessages(), store.getFolder("INBOX"));
                    }
                    trashfolder.close();
                    Main.mylog.append("весь архив обработан\n");
                    return;
                } catch (Exception e) {
                    Main.mylog.append("в процессе обработки архива произошла ошибка:\n"+e.getMessage()+'\n');
                    return;
                }
            }

            if (dateArchive[0]==null ) Main.mylog.append("дата с не распознанна или не введена\n");
            if (dateArchive[1]==null ) Main.mylog.append("дата по не распознанна или не введена\n");

            //////////////////////время получено из формы
            /* разбор из qr code
            String regex = "(\\d{4}-\\d{2}-\\d{2})";
            Matcher m = Pattern.compile(regex).matcher(sss);
            Date mydate[]  = new  Date[3];
            m.find();
            mydate[0] = new SimpleDateFormat("yyyy-MM-dd").parse(m.group(1));
            m.find();
            mydate[1] = new SimpleDateFormat("yyyy-MM-dd").parse(m.group(1));
            Main.mylog.append(mydate[0]+"gggggg"+mydate[1]);
            */



            ARCHIVE_WITH_TERMS:{
                try {
                    Folder inboxfolder = store.getFolder("INBOX");
                    trashfolder.open(Folder.READ_WRITE);
                    if (trashfolder.getMessageCount() != 0) {
                        // OlderTerm Find messages that are older than a given interval (in seconds).
                        //YoungerTerm Find messages that are younger than a given interval (in seconds).
                        SearchTerm term = null;
                        if (dateArchive[0] != null && dateArchive[1] != null) {
                            SearchTerm newerThen = new ReceivedDateTerm(ComparisonTerm.GT, dateArchive[0]);
                            SearchTerm olderThen = new ReceivedDateTerm(ComparisonTerm.LT, dateArchive[1]);
                            term = new AndTerm(newerThen, olderThen);
                        }
                        if (dateArchive[1] == null) {
                            SearchTerm newerThen = new ReceivedDateTerm(ComparisonTerm.GT, dateArchive[0]);
                            term = newerThen;
                        }
                        if (dateArchive[0] == null) {
                            SearchTerm olderThen = new ReceivedDateTerm(ComparisonTerm.LT, dateArchive[1]);
                            term = olderThen;
                        }
                        Message[] messagesToMoveToInbox = trashfolder.search(term);
                        //Message[] messagesToMoveToInbox = trashfolder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
                        trashfolder.moveMessages(messagesToMoveToInbox, inboxfolder);
                    }
                    trashfolder.close();
                    return;
                }catch (Exception e) {
                    Main.mylog.append("в процессе обработки архива с датами произошла ошибка:\n"+e.getMessage()+'\n');
                    return;
                }
            }
        } else
        {//галка архива не стоит и архив не нужен, перемещаем последнее сообщение
            try {
                //trashfolder.open(Folder.READ_ONLY);
                trashfolder.open(Folder.READ_WRITE);
                Message[] m = new Message[1];
                m[0] = trashfolder.getMessage(trashfolder.getMessageCount());//получаем последнее сообщение (самое старое будет под номером 1)
                trashfolder.moveMessages(m, store.getFolder("INBOX"));
                trashfolder.close();
            } catch (Exception e) {
                Main.mylog.append("последнее письмо не найдено, почта пуста "+'\n' +e.getMessage()+'\n');
                return;
            }
        }
        return;
    }

    public static IMAPFolder get_trash_folder( Store store,Folder[] folders) throws MessagingException {
        try {
            for (Folder folder1 : folders) {
                if(!Main.user_email.getText().contains("@ya")) {
                    trashfolder = (IMAPFolder) store.getFolder(store.getFolder("Корзина").getFullName());
                    break;
                }
                if(folder1.getName().equalsIgnoreCase("Удаленные"))
                    trashfolder = (IMAPFolder) store.getFolder(store.getFolder("Удаленные").getFullName());
                if(folder1.getName().equalsIgnoreCase("Trash"))
                    trashfolder = (IMAPFolder) store.getFolder(store.getFolder("Trash").getFullName());
            }
        }catch (Exception e) {
            Main.mylog.append("корзина не нашлась:" +e.getMessage()+'\n');
            return null;
        }
        return trashfolder;
    }
    public static void getmymail(){
        Main.mylog.setText("");
        try {
            final String user = Main.user_email.getText();// "machosracho88@mail.ru";
            final String pass = Main.pass_email.getText();//"ZYtDfcz729!";    // пароль
            final String host;
            if(user.contains("@ya") )
                host  = "imap.yandex.ru";
            else
                host  = "imap.mail.ru";
            Properties props = new Properties();// Создание свойств
            //props.put("mail.debug", "true");//включение debug-режима
            props.put("mail.store.protocol", "imaps");//Указываем протокол - IMAP с SSL
            Session session = Session.getInstance(props);
            Store store = session.getStore();//System.out.println("you click11111");
            try {
                store.connect(host, user, pass);
            }catch (Exception e) {
                Main.mylog.append("Авторизация:" +e.getMessage()+'\n');
                return;
            }
            //вывод всех папок
            //Folder[] f = store.getDefaultFolder().list();
            //for(Folder fd:f)
            //    System.out.println(">> "+fd.getName());

            Folder[] folders = store.getDefaultFolder().list("*");

            //for(Folder fd:folders)
            //    System.out.println(">> "+fd.getFullName());
            //if(true)return;
            //определяем корзину
            get_trash_folder(store, folders);

            for (Folder folder : folders) {
                //Main.mylog.append(folder.getFullName()+"bbbbbbb\n");
                if ((folder.getType() & Folder.HOLDS_MESSAGES) != 0) {
                    MOVETOTRASH:{//перемещение всех писем в корзину
                        try {
                                if (!folder.getName().equalsIgnoreCase("Корзина") && !folder.getName().equalsIgnoreCase("Удаленные")
                                        && !folder.getName().equalsIgnoreCase("Trash") && !folder.getName().equalsIgnoreCase("TRASH")
                                        && !(folder == trashfolder) && folder.getMessageCount()!=0) {
                                    IMAPFolder myStrangeFolder = (IMAPFolder) store.getFolder(folder.getFullName());
                                    myStrangeFolder.open(Folder.READ_WRITE);
                                    if (myStrangeFolder.getMessageCount() != 0) {
                                        myStrangeFolder.moveMessages(myStrangeFolder.getMessages(), trashfolder);
                                    }
                                    //myStrangeFolder.copyMessages(myStrangeFolder.getMessages(),trashfolder);
                                    myStrangeFolder.close();
                                }
                            }
                        catch (Exception e)
                            {
                                Main.mylog.append("ошибка переноса сообщений:\n"+folder.getFullName()+"\n" +e.getMessage()+'\n');
                            }
                    }

                }
            }

            for (Folder folder : folders) {
                //Main.mylog.append(folder.getFullName()+"bbbbbbb\n");
                if ((folder.getType() & Folder.HOLDS_MESSAGES) != 0) {
                    DELETEFOLDER:
                    //удаление папок
                    {
                        if (
                                !(folder.getName().equalsIgnoreCase("INBOX") ||
                                        folder.getName().equalsIgnoreCase("Спам") ||
                                        folder.getName().equalsIgnoreCase("Отправленные") ||
                                        folder.getName().equalsIgnoreCase("Черновики") ||
                                        folder.getName().equalsIgnoreCase("Корзина") ||
                                        folder.getName().equalsIgnoreCase("Trash") ||
                                        folder.getName().equalsIgnoreCase("Удаленные") ||
                                        folder.getName().equalsIgnoreCase("Social") ||
                                        folder.getName().equalsIgnoreCase("Newsletters") ||
                                        folder.getName().equalsIgnoreCase("ToMyself") ||
                                        folder.getName().equalsIgnoreCase("News"))
                        ) {
                            //Main.mylog.append(folder.getName() + ":" + folder.getFullName() + '\n');
                            IMAPFolder myStrangeFolder = (IMAPFolder) store.getFolder(folder.getFullName());
                            try {
                                myStrangeFolder.delete(true);
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }


            Main.mylog.append("все папки обработаны\n");

            archive_render(store);//if (true)return;
            Main.mylog.append("чистка завершена\n");
            Main.mylog.append(user+" :\n");
            Folder[] f = store.getDefaultFolder().list();
            for(Folder fd:f)
            {
                Main.mylog.append(fd.getName() +"::"+ fd.getMessageCount()+"\n");
            }
            Main.mylog.append("____________\n");
            /*
            //блок работает
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            Message m = inbox.getMessage(inbox.getMessageCount());//получаем последнее сообщение (самое старое будет под номером 1)
            Message[] messages = inbox.getMessages();
            for (int i=0; i < messages.length; ++i) {
                Message msg = messages[i];
                String subject = msg.getSubject();
                processSaveToFile(msg, subject);

            }*/
            /*Multipart mp = (Multipart) m.getContent();
            BodyPart bp = mp.getBodyPart(0);
            String my_message = (String)m.getContent();
            Выводим содержимое на экран
            System.out.println(bp.getContent());
            System.out.println(my_message);*/



        } catch (Exception e) {
            Main.mylog.append(e.getMessage()+"\n");
            Main.mylog.append(e.getStackTrace()+"\n");
            System.out.println(e.getMessage());
            //return e.getMessage();            // Always must return something
            return;
        }
        //return "";
        return;
    }

    private static void processSaveToFile (javax.mail.Message msg, String subject)
            throws MessagingException, IOException
    {
        String whereToSave = PathToSave + sanitizeFilename(subject) + ".eml";
        OutputStream out = new FileOutputStream(new File(whereToSave));
        try {
            msg.writeTo(out);
        }
        finally {
            if (out != null) { out.flush(); out.close(); }
        }
    }

    private static String sanitizeFilename(String name) {
        return name.replaceAll("[:\\\\/*?|<> \"]", "_");
    }
}
