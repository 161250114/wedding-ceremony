package com.wedding.rec_search_check.controller;

import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@ServerEndpoint(value = "/chatRoom/{username}") // 当创建好一个（服务）端点之后，将它以一个指定的URI发布到应用当中，这样远程客户端就能连接上它了
public class ChatRoomController {
    private static List<Session> sessions = new ArrayList<Session>();

    @OnOpen
    public void OnOpen(Session session, @PathParam(value = "username") String username) {
        sessions.add(session);
        sendTextMsg("好友 [" + username + "]加入聊天");
    }

    @OnMessage
    public void OnMsg(String msg, @PathParam(value = "username") String username) {
        sendTextMsg(username + ":" + msg);
    }

    @OnClose
    public void OnClose(Session session, @PathParam("username") String username) throws IOException {
        sessions.remove(session);
        sendTextMsg("好友 [" + username + "] 退出聊天");
    }

    @OnError
    public void OnError(Throwable e) {
        e.printStackTrace();
    }

    private void sendTextMsg(String msg) {
        for (Session session : sessions) {
            session.getAsyncRemote().sendText(msg);
        }
    }
}
