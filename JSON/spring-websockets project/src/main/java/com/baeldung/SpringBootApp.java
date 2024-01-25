package com.baeldung;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
/*

docker env:
    WEB_SOCKET_WITH_PORT = ws://localhost:8080/path
    FILE_JSON_PATH = ..
    FILE_JSON_PATH_BUF = ..

 */

enum LocalConfig {
    INSTANCE;
    public String WEB_SOCKET_WITH_PORT = System.getenv("WEB_SOCKET_WITH_PORT") == null ? "ws://localhost:8080/path" : System.getenv("WEB_SOCKET_WITH_PORT");
    //private int port = System.getenv("PORT") == null ? -1 : Integer.parseInt(System.getenv("PORT"));
    public String FILE_JSON_PATH = System.getenv("FILE_JSON_PATH") == null ? "./FILE_JSON_PATH" : System.getenv("FILE_JSON_PATH");
    public String FILE_JSON_PATH_BUF = System.getenv("FILE_JSON_PATH_BUF") == null ? "./FILE_JSON_PATH_BUF" : System.getenv("FILE_JSON_PATH_BUF");
}

@SpringBootApplication
@EnableScheduling
public class SpringBootApp extends SpringBootServletInitializer {
    public static void main(String[] args) throws URISyntaxException {
        System.out.println("service json started");
        //обработка ошибки неназначенного сокета
        if (System.getenv("WEB_SOCKET_WITH_PORT") == null) {
            jsonStringObject.jsonString = "json socket env not found";
            jsonStringObject.writetofile();
            System.exit(0);
        }
        //обработка не назначеннго имени файла
        if (System.getenv("FILE_JSON_PATH") == null) {
            jsonStringObject.jsonString = "FILE_JSON_PATH env not found";
            jsonStringObject.writetofile();
            System.exit(0);
        }

        //SpringApplication.run(SpringBootApp.class, args);

        WebSocketClient client = new ReactorNettyWebSocketClient();
        URI url = new URI(LocalConfig.INSTANCE.WEB_SOCKET_WITH_PORT);
        client.execute(url, session ->
                session.receive()
                        //.doOnNext(System.out::println)
                        .doOnNext(str -> jsonStringObject.addToBuffer(str))
                        .then());
    }

    public static class jsonStringObject {
        static String jsonString = "";
        static String buffer = new String();

        static void addToBuffer(WebSocketMessage str) {
            String bufToAdd = str.toString();
            buffer = buffer + bufToAdd;
            tryToGenerateNewVal(buffer);
        }

        static void tryToGenerateNewVal(String buf) {
            String[] parts = buf.split("\n");
            if (parts.length == 1) {
                buffer = buffer + parts[0];
            } else {
                buffer = buffer + parts[0];
                jsonString = buffer;
                buffer = parts[1];
                writetofile();
            }
        }

        public static void writetofile() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(LocalConfig.INSTANCE.FILE_JSON_PATH)))) {
                if (jsonStringObject.jsonString.length()>60)
                    jsonStringObject.jsonString = prettyPrintJsonUsingDefaultPrettyPrinter(jsonStringObject.jsonString);
                writer.write(jsonStringObject.jsonString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (System.getenv("FILE_JSON_PATH_BUF") == null) return;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(LocalConfig.INSTANCE.FILE_JSON_PATH_BUF)))) {

                //String buffer = prettyPrintJsonUsingDefaultPrettyPrinter(jsonStringObject.buffer);
                writer.write(jsonStringObject.buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //json butifier method
        public static String prettyPrintJsonUsingDefaultPrettyPrinter(String uglyJsonString) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            Object jsonObject = objectMapper.readValue(uglyJsonString, Object.class);
            String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
            return prettyJson;
        }
    }
}