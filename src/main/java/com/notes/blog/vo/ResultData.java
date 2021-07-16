package com.notes.blog.vo;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.Serializable;

/**
 * @author HLJ
 * @Email: 1603020074@qq.com
 * @Date: 2019年4月3日 下午2:31:53
 * @Description: 统一响应封装对象
 */
public class ResultData implements Serializable {
    /**
     * 响应状态
     */
    private Integer status;


    /**
     * 响应数据
     */
    private Object data;


    /**
     * 响应消息
     */
    private String msg;


    public ResultData() {

    }

    public ResultData(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static ResultData ok() {
        ResultData response = new ResultData();
        response.setStatus(200);
        response.setMsg("request success!");
        return response;
    }

    public static ResultData ok(Object data) {
        ResultData response = new ResultData();
        response.setStatus(HttpStatus.OK.value());
        response.setMsg("request success!");
        response.setData(data);
        return response;
    }

    public static ResultData ok(String msg) {
        ResultData response = new ResultData();
        response.setStatus(200);
        response.setMsg(msg);
        return response;
    }


    public static ResultData ok(String msg, Object data) {
        ResultData response = new ResultData();
        response.setStatus(200);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }


    public static ResultData error() {
        ResultData response = new ResultData();
        response.setStatus(500);
        response.setMsg("处理失败");
        return response;
    }

    public static ResultData error(String msg) {
        ResultData response = new ResultData();
        response.setStatus(500);
        response.setMsg(msg);
        return response;
    }

    public static ResultData error(String msg, Object data) {
        ResultData response = new ResultData();
        response.setStatus(500);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }


    public void setData(Object data) {
        this.data = data;
    }


    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }


    /**
     * 获取success ServerResponse
     *
     * @param resultDataMono
     * @return
     */
    public static Mono<ServerResponse> getSuccess(Mono<ResultData> resultDataMono) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(resultDataMono, ResultData.class);
    }

    /**
     * 获取success ServerResponse
     *
     * @param data
     * @return
     */
    public static Mono<ServerResponse> getSuccess(Object data) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(ResultData.ok(data)), ResultData.class);
    }

    /**
     * 获取error ServerResponse
     *
     * @param resultData
     * @return
     */
    public static Mono<ServerResponse> getError(ResultData resultData) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(resultData), ResultData.class);
    }

    /**
     * 获取 error ServerResposne
     *
     * @param errorMsg
     * @return
     */
    public static Mono<ServerResponse> getError(String errorMsg) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(ResultData.error(errorMsg)), ResultData.class);
    }

    /**
     * 获取无权限 ServerResposne
     *
     * @return
     */
    public static Mono<ServerResponse> getNoPermission() {
        return ServerResponse.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).syncBody("无操作权限!");
    }
}
