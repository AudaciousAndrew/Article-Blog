function check_login_password(login, password) {
    if (login.length < 5 || login.length > 20)
        return "Длина логина должна быть в диапазоне от 5 до 20 символов!";
    if (password.length < 5 || password.length > 20)
        return "Длина пароля должна быть в диапазоне от 5 до 20 символов!";
}

function signup_validate(form){

    let login = form.login.value;
    let password = form.password.value;
    let repeat_password = form.repeatPassword.value;
    let error = check_login_password(login, password);

    if (password !== repeat_password)
        error = "Пароли не совпадают!";

    if (error){
        $("#errorLabel").text(error);
        return false;
    } else
        return true;
}

function login_validate(form){

    let login = form.login.value;
    let password = form.password.value;
    let error = check_login_password(login, password);

    if (error){
        $("#errorLabel").text(error);
        return false;
    } else
        return true;
}

function dots_validate(form){
    let x = form.X.value;
    let y = form.Y.value;
    let r = form.R.value;
    let error;

    if (y <= -5 || y >= 3) { error = "Y - incorrect value range"; }
    if (isNaN(y)) { error = "Y value is incorrect !"; }
    if (y === "") { error = "Y is empty !"; }
    if (y.length > 7) { error = "Y is too large !"; }

    if (r <= 0) { error = "R is negative !"; }

    if (error){
        $("#errorLabel").text(error);
        return false;
    } else
        $("#errorLabel").text("");
        return true;
}