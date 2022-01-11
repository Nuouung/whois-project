const emptyValidate = /\s/;
const emailValidate =
  /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
const passwordValidate = /^(?=.*[a-zA-Z0-9])(?=.*\W).{10,20}$/;

const loginForm = document.getElementById("loginForm");
const emailForm = document.getElementById("emailr");
const passwordForm = document.getElementById("password");
const formButton = document.getElementById("formButton");
const emailFailText = document.getElementById("emailFailText");
const individual = document.getElementById("individual");
const company = document.getElementById("company");

emailForm.addEventListener("focusout", emailCheck);
passwordForm.addEventListener("focusout", passwordCheck);
formButton.addEventListener("click", finalCheck);

function radioCheck() {
  console.log("개인/기업 검증");
  if (!individual.checked && !company.checked) {
    alert("개인 혹은 기업 로그인 버튼을 활성화해 주세요.");
    return false;
  }
  return true;
}

function emailCheck() {
  console.log("이메일검증");
  if (
    !emailValidate.test(emailForm.value) ||
    emptyValidate.test(emailForm.value)
  ) {
    emailForm.style.borderColor = "red";
    return false;
  }
  emailForm.style.borderColor = "#003cf0";
  return true;
}

function passwordCheck() {
  console.log("비밀번호검증");
  if (
    !passwordValidate.test(passwordForm.value) ||
    emptyValidate.test(passwordForm.value)
  ) {
    passwordForm.style.borderColor = "red";
    return false;
  }
  passwordForm.style.borderColor = "#003cf0";
  return true;
}

function finalCheck() {
  console.log("최종검증");
  if (validFailCheck()) {
    formButton.type = "button"; // 버튼이 submit 하지 못하도록 막는다. (더 좋은 방법이 있을 것 같지만 자바스크립트를 그렇게 잘 알지 못한다.)
    return false;
  }
  formButton.type = "submit"; // 검증이 정상적인 경우 버튼을 submit으로 만든다.
  document.loginForm.submit();
}

function validFailCheck() {
  if (!radioCheck()) return true;
  if (!emailCheck()) return true;
  if (!passwordCheck()) return true;
  let answer;
  if(document.getElementById("individual").checked){
    answer = document.getElementById("emailr").value +"."+ document.getElementById("individual").value;
  } else {
    answer = document.getElementById("emailr").value +"."+ document.getElementById("company").value;
  }
  document.getElementById("email").value=answer;
  return false;
}
