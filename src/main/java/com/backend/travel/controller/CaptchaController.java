package com.backend.travel.controller;

import cn.hutool.captcha.*;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.lang.UUID;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.constant.CheckCode;

import com.backend.travel.key.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping( "/captcha" )
@Slf4j
public class CaptchaController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping( "/code" )
    public BaseResponse<HashMap<String, Object>> getCaptcha(HttpServletResponse response, HttpServletRequest request) throws IOException {
        // 创建验证码文本
        String capText = defaultKaptcha.createText();
        // 创建验证码图片
        BufferedImage image = defaultKaptcha.createImage(capText);

        // 将验证码文本放进 Session 中
        // CheckCode code = new CheckCode(capText);
//        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, code);

        // 将验证码图片返回，禁止验证码图片缓存
//        response.setHeader("Cache-Control", "no-store");
//        response.setHeader("Pragma", "no-cache");
//        response.setDateHeader("Expires", 0);
//        response.setContentType("image/jpeg");

//        CheckCode checkCode = (CheckCode) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
//        log.info("验证码文本：" + checkCode.getCode());
//        ImageIO.write(image, "jpg", response.getOutputStream());


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        Base64Encoder encoder = new Base64Encoder();
        String str = "data:image/jpeg;base64,";

        String base64Img = str + encoder.encode(outputStream.toByteArray());
        UUID randomUUID = UUID.randomUUID();
        // 存入redis
        String res = JSONUtil.toJsonStr(capText);
        redisTemplate.opsForValue().set(randomUUID.toString(), res, 60 * 5);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("uuid", randomUUID.toString());
        hashMap.put("img", base64Img);

        return ResultUtils.success(hashMap);
    }
}
