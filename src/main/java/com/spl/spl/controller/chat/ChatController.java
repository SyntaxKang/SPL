package com.spl.spl.controller.chat;

import com.spl.spl.dto.chat.ChatMessage;
import com.spl.spl.repository.chat.ChatRoomRepository;
import com.spl.spl.repository.users.UsersRepository;
import com.spl.spl.service.chat.ChatService;
import com.spl.spl.service.chat.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final JwtTokenProvider jwtTokenProvider;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatService chatService;
    private final UsersRepository userRepository;

    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(ChatMessage message, @Header("token") String token) {
        String nickname = jwtTokenProvider.getUserNameFromJwt(token);

        System.out.println("nickname : "+nickname);
        System.out.println("nickname.length() : "+nickname.length());

        String cutName = nickname.substring(87,90);
        System.out.println("CutName in message controller : "+cutName);

        // 로그인 회원 정보로 대화명 설정
      //  nickname = userRepository.findByEmail(nickname).getNickname();
        message.setSender(cutName);

        // 채팅방 입장시에는 대화명과 메시지를 자동으로 세팅한다.
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setSender("[알림]");
            message.setMessage(cutName + "님이 입장하셨습니다.");
        }

        // 채팅방 인원수 세팅
        message.setUserCount(chatRoomRepository.getUserCount(message.getRoomId()));
        // Websocket에 발행된 메시지를 redis로 발행(publish)
        chatService.sendChatMessage(message);
    }
}



