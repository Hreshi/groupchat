let submitBtn = document.getElementById('submit-btn')
let changeBtn = document.getElementById('change-btn')
let passAgain = document.getElementById('pass-again')
let authForm = document.getElementById('form')
let loginActivity = true

changeBtn.addEventListener('click', function () {
    loginActivity = !loginActivity
    if(loginActivity) {
        submitBtn.innerText = "Sign In"
        changeBtn.innerText = "Don't have account ? Sign Up"
        passAgain.hidden = true
        authForm.action = "/signin"
    } else {
        submitBtn.innerText = "Sign Up"
        changeBtn.innerText = "Already have account ? Sign In"
        passAgain.hidden = false
        authForm.action = "/signup"
    }
})