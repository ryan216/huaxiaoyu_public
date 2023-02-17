package com.example.huaxiaoyu.controller.friends;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.huaxiaoyu.domain.LoginUser;
import com.example.huaxiaoyu.domain.User;
import com.example.huaxiaoyu.domain.friends.Friends;
import com.example.huaxiaoyu.domain.notice.Notice;
import com.example.huaxiaoyu.service.MessageService;
import com.example.huaxiaoyu.service.UserService;
import com.example.huaxiaoyu.service.friends.FriendsService;
import com.example.huaxiaoyu.service.notice.NoticeService;
import com.example.huaxiaoyu.utils.JwtUtil;
import com.example.huaxiaoyu.utils.R;
import io.jsonwebtoken.Claims;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: 你与黎明
 * @Description: 朋友,0——删除好友1——申请好友2——回复好友申请3——申请获取联系方式4——回复申请获取联系方式
 * @create: 2022-12-19 14:54
 * @Version: 1.0
 */
@RestController
@RequestMapping()
public class FriendsController {
    @Autowired
    private FriendsService friendsService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;




    @GetMapping("/friend/getFriendList")
    public R get(){
        List<User> users = friendsService.get();
        return new R("朋友查询成功",users,true);
    }
    
    /**
     * @param : 
     * @return R 
     * @author 
     * @description 获取消息列表
     * @date 2023/2/16 14:57
     */
    @GetMapping("/message/getMessageList")
    public  R getNotice(){
        HashMap<String, List<HashMap<String, Object>>> res = noticeService.get();
        return new R("消息获取成功",res,true);

    }

    /**
     * @param receiveId:
     * @return R
     * @author
     * @description 删除好友
     * @date 2023/2/16 9:27
     */
    @PostMapping("/friend/deleteFriend")
    public  R delete(@RequestParam("opponentId") Integer receiveId){
        noticeService.add(receiveId,0,null);

        Boolean flag2 = friendsService.delete(receiveId);

        HashMap<String,Boolean> res =new HashMap<>();
        res.put("isSuccess",flag2);
        if(flag2){
            return new R("好友删除成功！",res,true);
        }
        return new R("好友删除失败！",res,true);

    }

    /**
     * @param receiveId:
     * @return R
     * @author
     * @description 添加好友
     * @date 2023/2/16 9:27
     */
    @PostMapping("/friend/applyFriend")
    public  R applyFriend(@RequestParam("opponentId") Integer receiveId){
        Boolean flag = noticeService.add(receiveId, 1, null);

        HashMap<String,Boolean> res =new HashMap<String, Boolean>();
        res.put("isSuccess",flag);
        if(flag){
            return new R("好友申请成功！",res,true);
        }
        return new R("好友申请失败！",res,true);

    }


    /**
     * @param messageId:
     * @param opponentId:
     * @param result:
     * @return R
     * @author
     * @description 同意好友申请
     * @date 2023/2/16 9:26
     */
    @PostMapping("/message/replyFriend")
    public  R replyFriend(@RequestParam("messageId") Integer messageId,
                  @RequestParam("opponentId") Integer opponentId,
                  @RequestParam("result") Integer result){
        Notice notice = noticeService.getById(messageId);
        notice.setStatus(1);
        boolean flag1 = noticeService.updateById(notice);

        Boolean flag = noticeService.add(opponentId, 2, result);

        HashMap<String,Boolean> res =new HashMap<String, Boolean>();
        res.put("isSuccess",flag);

        if(result==1){
            Boolean flag2 = friendsService.add(opponentId);
        }
        if(flag){
            return new R("好友申请回复成功！",res,true);
        }
        return new R("好友申请回复失败！",res,true);

    }

    @PostMapping("/friend/applyPhoneNum")
    public  R applyPhoneNum(@RequestParam("opponentId") Integer opponentId) {
        Boolean flag = noticeService.add(opponentId, 3, null);
        HashMap<String,Boolean> res =new HashMap<String, Boolean>();
        res.put("isSuccess",flag);
        if(flag){
            return new R("申请获取联系方式成功！",res,true);
        }
        return new R("申请获取联系方式失败！",res,true);


    }
    /**
     * @param messageId:
     * @param opponentId:
     * @param result:
     * @return R
     * @author
     * @description 回复申请获取联系方式
     * @date 2023/2/16 14:56
     */
    @PostMapping("/message/replyPhoneNum")
    public  R replyPhoneNum(@RequestParam("messageId") Integer messageId,
                            @RequestParam("opponentId") Integer opponentId,
                            @RequestParam("result") Integer result){
        Notice notice = noticeService.getById(messageId);
        notice.setStatus(1);
        boolean flag1 = noticeService.updateById(notice);

        Boolean flag = noticeService.add(opponentId, 4, result);


        HashMap<String,Boolean> res =new HashMap<String, Boolean>();
        res.put("isSuccess",flag);
        if(flag){
            return new R("回复申请获取联系方式成功！",res,true);
        }
        return new R("回复申请获取联系方式失败！",res,true);
    }
    
    /**
     * @param messageId: 
     * @return R 
     * @author 
     * @description 修改消息的已读状态
     * @date 2023/2/16 14:57
     */
    @PostMapping("/message/updateStatusOfMsg")
    public  R updateStatusOfDeleteMsg(@RequestParam("messageId") Integer messageId){
        Notice notice = noticeService.getById(messageId);
        notice.setStatus(1);
        boolean flag = noticeService.updateById(notice);

        HashMap<String,Boolean> res =new HashMap<String, Boolean>();
        res.put("isSuccess",flag);
        if(flag){
            return new R("修改好友消息的已读状态成功！",res,true);
        }
        return new R("修改好友消息的已读状态失败！",res,true);
    }

//
    @GetMapping("/record/getRecordList")
    public  R getRecordList(){
        HashMap<String,List<HashMap<String, Object>>> res=new HashMap<>();
        List<HashMap<String, Object>> res1 = messageService.get_records();
        res.put("recordList",res1);
        return new R("聊天记录列表获取成功",res,true);

    }


}
