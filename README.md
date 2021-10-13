web-application-server Study
======================

웹 애플리케이션 서버 실습을 위한 레포
박재성님의 [HTTP Web Server 강의](https://www.youtube.com/playlist?list=PLqaSEyuwXkSqV88SwDxuY56xmj6KsmzRN)를 기반으로 진행하는 Web Server Study 
## Step


1. **[슬라이드 쉐어](https://www.slideshare.net/javajigi/http-web-server?qid=5598469d-8303-4ef0-9fe6-1e1b3d75ffcc&v=&b=&from_search=5)  강의 자료를 통해 요구사항을 보고 스스로 구현을 진행한다.**
2. **요구사항을 구현했다면 [강의](https://www.youtube.com/playlist?list=PLqaSEyuwXkSqV88SwDxuY56xmj6KsmzRN)를 보고 리팩토링을 진행한다.** 
3. **다른 사람이 진행한 리팩토링 한 코드를 보고, 코드리뷰를 진행 한다. (질문 포함)**
4. **각각의 단계를 학습할때마다, HTTP상에서 Client와 Server가 어떻게 데이터를 주고받고, 어떤 동작원리로 실행되는지를 학습해서 학습 기록 레포에 올린다.**
5. **과정이 마무리 된다면, 강의를 보지않고 처음부터 다시 진행 해본다.**
---

1. Request Header 출력
2. Request Line에서 path 분리
3. path에 해당하는 파일 읽어 응답하기
4. Post로 받은 Body(회원가입) 추출하기
5. 회원가입이 완료되면 302 redirect(index.html)
6. 쿠키 값(logined=true)확인하기
7. 로그인 성공시 index.html 이동, 실패시 login_failed.html 이동


스프링 처럼 구현하기

만들 어노테이션
Controller, RequestMapping, GetMapping, PostMapping



컨트롤러 어노테이션 붙은 클래스를 찾는다.
 
Get Post가 붙은 메소드를 찾는다. detectRequestMethods

RequestInfo를 만든다. 

Map에 저장한다. -> RequestHandlers


각 클래스에 RequestMapping이나 GetMapping,PostMapping이 붙은 메소드를 찾는다.
메타데이터로 들어있는 url에 맞게 응답한다.

@Request("
class HelloContoller

@GetMapping(index.html)
void 200
메소드 , url

get post url


Map<RequestInfo,Method>
---
![image](https://user-images.githubusercontent.com/73640185/136883165-82fecb81-0d67-49f8-87c5-8834a8ea9c62.png)
---
요청이 들어온다

RequestInfo로 올바른 메소드를 찾아온다.

invoke해서 나온(문자열,ReturnValue)

파일? 응답(reponse)



RequestHandlerContatainer를 밖에서 생성해서 넣어줄껀가?
안에서 생성할껀가?

요청 -> DispatcherServlet의 doService() 호출
->doDispatch()에서 올바른 HnadlerMethod를 찾는다.
method를 호출할 수 있는 adapter를 찾는다. adpter로 method실행
ReturnValueHandler에가 handlerMethod의 결과값으로 적절한
Reponse생성후 응답


질문 adapter가 왜 필요할까?
simple controller, rest controller 등의 다른 방식으로 처리해야하는 것들이
존재 하기 때문

return index.html;

String value = invoke;

reponse(value);
200 300

