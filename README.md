# gamsungjin
일반적인 홈페이지 형태의 커뮤니티를 이용할 수 있습니다

<br>

## 사용한 기술
- Java
- Spring
- JSP
- JavaScript
- MySQL
- HTML
- CSS

</br>

## ERD 설계
![감성진](https://user-images.githubusercontent.com/129938274/230548410-f1a366aa-518f-4a3f-bdc0-2f6c257276d9.png)

</br>

## 주요 기능
- 가입한 회원만 이용할 수 있게 회원가입, 로그인 기능
- 게시글을 수정, 삭제할 수 있는 기능
- 이미지 업로드 기능
- 댓글 작성, 삭제 기능

<details>
<summary><b>주요 기능 펼치기</b></summary>

## 회원가입 주요 기능
회원 가입시 항목을 체크하고 작성하지 않은 부분이 있다면 넘어가지 않습니다

예시) 아이디 부분
```javascript
if (loginId == "") {
  alert("아이디를 입력해주세요.");
  return;
}
```

비밀번호 일치 여부를 확인합니다
```javascript
if (password != passwordCheck) {
// 비밀번호 일치x
  $('#pwCheckNotOk').removeClass('d-none');
  $('#pwCheckOk').addClass('d-none');
  return;
} else {
// 비밀번호 일치o
  $('#pwCheckNotOk').addClass('d-none');
  $('#pwCheckOk').removeClass('d-none');
}
```

## 게시글 작성 시 이미지 업로드 주요 기능
이미지 파일은 저장소에 저장되고 주소(url) 생성 후 DB에 입력하는 방식입니다
```java
// 컴퓨터에 저장될 경로
public final static String FILE_UPLOAD_PATH = "/home/ec2-user/images/";
	
// 파일을 컴퓨터에 저장 -> url path 리턴
public String saveFile(int userId, MultipartFile file) throws IOException {
  // 파일 디렉토리 경로 만들기
  String directoryName = userId + "_" + System.currentTimeMillis() + "/";	// ex) 1_1620995857660/
  String filePath = FILE_UPLOAD_PATH + directoryName;

  File directory = new File(filePath);
  if (directory.mkdir() == false) {	// 업로드할 경로에 폴더 생성
    // 디렉토리 생성 실패
    return null;
  }

  // 파일 업로드 -> byte 단위로 업로드
  byte[] bytes = file.getBytes();
  Path path = Paths.get(filePath + file.getOriginalFilename()); // getOriginalFilename() => input에서 올린 파일명
  Files.write(path, bytes);

  // 이미지 url 만들어 리턴
  return "/images/" + directoryName + file.getOriginalFilename();
}
```

게시글 삭제 시 이미지 파일도 삭제합니다
```java
public void deleteFile(String imagePath) throws IOException {
  Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/", ""));
  
  // 이미지 삭제
  if (Files.exists(path)) {
  Files.delete(path);
  }
  // 디렉토리 삭제
  path = path.getParent();
  if (Files.exists(path)) {
    Files.delete(path);
  }
}
```

## 댓글 작성 시 주요 기능
댓글은 로그인한 사용자만 댓글쓰기 창이 활성화됩니다
```jsp
<c:if test="${not empty userId}">
  <div>
    <input type="text" name="content" placeholder="댓글을 입력해주세요.">
    <div>
      <button type="button" id="commentUpBtn" data-post-id="${post.id}">등록</button>
    </div>
  </div>
</c:if>
```

댓글 삭제 버튼이 있고 로그인 사용자와 댓글 작성자가 같아야 버튼이 활성화됩니다
```jsp
<c:if test="${userId eq comment.userId}">
  <img src="???" class="commentDelBtn" alt="del_icon" data-comment-id="${comment.id}">
</c:if>
```

</details>
