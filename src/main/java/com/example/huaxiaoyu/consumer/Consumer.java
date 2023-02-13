package com.example.huaxiaoyu.consumer;

import com.alibaba.fastjson.JSONObject;
import com.example.huaxiaoyu.domain.User;
import com.example.huaxiaoyu.service.Impl.MessageServiceImpl;
import com.example.huaxiaoyu.service.Impl.UserServiceImpl;
import com.example.huaxiaoyu.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: 你与黎明
 * @Description: consumer
 * @create: 2022-11-15 11:12
 * @Version: 1.0
 */

//@ServerEndpoint(value = "/websocket")
@ServerEndpoint(value = "/websocket/{userId}")
@Component

public class Consumer{

    //用来存放每个客户端对应的MyWebSocket对象。
    final public static ConcurrentHashMap<Integer,Consumer> users = new ConcurrentHashMap<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private User user;


    private  static  MessageServiceImpl messageService;

    private  static UserServiceImpl userService;

    public  static RestTemplate restTemplate;

    private final static String addUserUrl = "http://127.0.0.1:9093/user/add/";
    private final static String removeUserUrl = "http://127.0.0.1:9093/user/remove/";


    public   static RedisCache redisCache;

    @Autowired
    public  void setRedisCache(RedisCache redisCache){
        Consumer.redisCache=redisCache;
    }

    @Autowired
    public void setMessageService(MessageServiceImpl messageService){
         Consumer.messageService=messageService;
    }

    @Autowired
    public  void setUserService(UserServiceImpl userService){
        Consumer.userService=userService;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        Consumer.restTemplate = restTemplate;
    }


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("userId") Integer userId) {
//    public void onOpen(Session session) {
//        String userid="1";
        this.session = session;
        Integer id = userId;
        System.out.println(id);

        this.user= userService.getById(id);

        users.put(id,this);

        System.out.println("new connection,total:" + users.size());


        String msg="websocket connect success!";
        JSONObject resp=new JSONObject();
        resp.put("event", "");
        resp.put("data", msg);
        resp.put("flag",true);
//        this.session.getAsyncRemote().sendText("websocket connect success!");
        this.session.getAsyncRemote().sendText(resp.toJSONString());

    }


    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        users.remove(this.user.getId());
        stopMatching();
        System.out.println("1 close！now nums:" + users.size());
    }
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("new info::" + message);
        JSONObject data = JSONObject.parseObject(message);

        System.out.println(data.get("event"));
        String event = data.getString("event");
//        String sendId=data.getInteger("sendId").toString();

        if ("start-matching".equals(event)) {
            startMatching();
        } else if ("stop-matching".equals(event)) {
            stopMatching();
            this.session.getAsyncRemote().sendText("stop matching success");
        }
        String sendId=this.user.getId().toString();
//        System.out.println(sendId);
        String bId=redisCache.getCacheObject(sendId);
        User userB = userService.getById(bId);
        if(redisCache.getCacheObject(sendId)!=null){
            JSONObject resp=new JSONObject();
            resp.put("event", "start-chat");
            JSONObject resp_data=new JSONObject();
            JSONObject opponent=new JSONObject();
            opponent.put("nickname", userB.getNickname());
            opponent.put("id", userB.getId());
            opponent.put("sex", userB.getSex());
            opponent.put("departmentCode", userB.getDepartmentCode());
            opponent.put("interestCodeList", userB.getInterestCodeList());
            opponent.put("headPhoto",userB.getHeadPhoto());
            resp_data.put("opponent",opponent);
            resp.put("data", resp_data);
            resp.put("flag",true);
            this.session.getAsyncRemote().sendText(resp.toJSONString());
            redisCache.deleteObject(sendId);
        }
        if("message".equals(event)){
            System.out.println(data.get("data"));
            JSONObject data1 = (JSONObject) data.get("data");
            Integer receiveId= data1.getInteger("receiveId");
//            System.out.println(receiveId);
            users.get(receiveId).session.getAsyncRemote().sendText(message);
//                this.session.getAsyncRemote().sendText(message);
            }

    }
    /**
     * 发生错误时调用
     *
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("error!");
        error.printStackTrace();
    }

    public static void startChat(Integer aId, Integer bId) {

        System.out.println("开始发送心跳包！");
        User userA = userService.getById(aId);
        User userB = userService.getById(bId);

        JSONObject respA=new JSONObject();
        respA.put("event", "start-chat");
        respA.put("opponent_username", userB.getUsername());
        respA.put("opponent_userid", userB.getId());
        respA.put("opponent_sex", userB.getSex());
        respA.put("opponent_departmentCode", userB.getDepartmentCode());


        System.out.println(users.get(userA.getId()));

//        users.get(userA.getId()).getAsyncRemote().sendText(respA.toJSONString());
        System.out.println(userA.getId());
//        users.get(userA.getId()).getBasicRemote().sendText("hello");

        if (users.get(userA.getId()) != null){
            users.get(userA.getId()).sendMessage("hello1");
        }

//        users.get(userA.getId()).session.getAsyncRemote().sendText(respA.toJSONString());

        JSONObject respB=new JSONObject();
        respB.put("event", "start-chat");
        respB.put("opponent_username", userA.getUsername());
        respB.put("opponent_userid", userA.getId());
        System.out.println(users.get(userB.getId()));
//        users.get(userB.getId()).getAsyncRemote().sendText(respB.toJSONString());
//        users.get(userB.getId()).session.getAsyncRemote().sendText(respB.toJSONString());

    }

    private void startMatching() {
//        System.out.println("start matching!");
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("user_id", this.user.getId().toString());
        data.add("sex", this.user.getSex());
        String s = restTemplate.postForObject(addUserUrl , data , String.class);
        System.out.println(s);
    }

    private void stopMatching() {
        System.out.println("stop matching");
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("user_id", this.user.getId().toString());
        String s = restTemplate.postForObject(removeUserUrl, data, String.class);
        System.out.println(s);
    }

    /**
     * 群发自定义消息
     * */
//    public  void broadcast(String message){
//        for (Consumer item : users.values()) {
//            //同步异步说明参考：http://blog.csdn.net/who_is_xiaoming/article/details/53287691
//            //this.session.getBasicRemote().sendText(message);
//            item.session.getAsyncRemote().sendText(message);//异步发送消息.
//        }
//    }

    //单发
    public void sendMessage(String message) {
        synchronized (this.session) {
            try {
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

