web-application-server Study
======================

웹 애플리케이션 서버 실습을 위한 레포
박재성님의 [HTTP Web Server 강의](https://www.youtube.com/playlist?list=PLqaSEyuwXkSqV88SwDxuY56xmj6KsmzRN)를 기반으로 진행하는 Web Server Study 
## Step 1


1. **[슬라이드 쉐어](https://www.slideshare.net/javajigi/http-web-server?qid=5598469d-8303-4ef0-9fe6-1e1b3d75ffcc&v=&b=&from_search=5)  강의 자료를 통해 요구사항을 보고 스스로 구현을 진행한다.**
2. **요구사항을 구현했다면 [강의](https://www.youtube.com/playlist?list=PLqaSEyuwXkSqV88SwDxuY56xmj6KsmzRN)를 보고 리팩토링을 진행한다.** 
3. **다른 사람이 진행한 리팩토링 한 코드를 보고, 코드리뷰를 진행 한다. (질문 포함)**
4. **각각의 단계를 학습할때마다, HTTP상에서 Client와 Server가 어떻게 데이터를 주고받고, 어떤 동작원리로 실행되는지를 학습해서 학습 기록 레포에 올린다.**
5. **과정이 마무리 된다면, 강의를 보지않고 처음부터 다시 진행 해본다.**

## Step 2
1. 미션을 완료했으면 Spring mvc 구조를 학습해본다.
2. 학습 후 간단히 구현 해 볼 수 있는 선에서 Spring mvc를 순수 자바로 만들어본다.
3. 꼭 필요하지 않은 부분은 구현하지 않고 진행한다.(@Component, DI 컨테이너 등등)
---
### 진행 상황
1. @GetMapping, @PostMapping, @RequestMapping, @Controller등 필요한 어노테이션 구현
2. 리플렉션을 통해 @Controller가 붙은 클래스를 가져오기
3. 가져온 클래스에 @GetMapping, @PostMapping처럼 @RequestMapping이 붙어 있는 어노테이션이 붙어 있는 메소드를 찾기
4. dispatcherServlet은 싱글톤으로 구현
5. dispatcherServlet이 찾은 메소드를 invoke하고 올바른 view를 찾아 Response를 만든다.
6. 현재 get이나 회원가입은 가능하나 css 적용 X
7. 이후에 css 적용 문제 해결 및 로그인 기능 추가 
