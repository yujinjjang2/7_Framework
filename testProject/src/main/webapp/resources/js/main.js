const inputNickname = document.getElementById("inputNickname");
const btn1 = document.getElementById("btn1");
const result1 = document.getElementById("result1");

btn1.addEventListener("click", () => {

    // fetch API를 이용해서 ajax
    // GET 방식 요청 (파라미터를 쿼리스트링으로 추가)

    // Promise : 비동기 함수 호출 또는 연산이 완료되었을 때
    //         이후에 처리할 함수나 에러를 처리하기 위한
    //         함수를 설정하는 모듈
    //         -> 비동기 연산의 최종 결과 객체

    fetch("/member/selectMemberTel?nickname=" + inputNickname.value)
    .then( resp => resp.text() ) // 응답 객체(자료형 1일때)를 문자열 형식으로 파싱
    .then( data => {
        // 데이터 가공
        console.log(data);
        result1.innerText = data;
    })
    .catch( err => console.log(err) );

});