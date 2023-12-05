<template>
  <div class="container">
    <section>
      <div class="img">
        <img src="..\assets\image 2.png" alt="" />
      </div>
      <div class="form">
        <h2 @click="changeStateLogin">
          <i class="fa-solid fa-arrow-left" style="color: #000000"></i>
        </h2>
        <h3>Here We Go!</h3>
        <div class="row">
          <div class="col">
            <label>Enter New Password</label>
            <input
              type="password"
              required
              v-model="newPassword"
              @input="checkPassword(this.newPassword)"
            />
            <div
              v-show="!passwordValid && newPassword.length > 0"
              class="alert alert-danger"
              role="alert"
            >
              <h5>
                Password Should Contain Capital Letter,Small Letter and Number
                and over 8 lentgh
              </h5>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <label>Confirm New Password</label>
            <input
              type="password"
              required
              v-model="confirmNewPassword"
              @blur="matchPassword(this.newPassword, this.confirmNewPassword)"
            />
            <div
              v-show="
                !matchPassword(this.newPassword, this.confirmNewPassword) &&
                confirmNewPassword.length > 0
              "
              class="alert alert-danger"
              role="alert"
            >
              <h5>Password Doesn't Match</h5>
            </div>
          </div>
        </div>
        <div class="row">
          <button @click="changePass" class="butn">Submit</button>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  props: ["email", "question", "answer"],
  data() {
    return {
      newPassword: "",
      confirmNewPassword: "",
      passwordLength: false,
      passwordCapital: false,
      passwordNumber: false,
      passwordsmall: false,
      passwordValid: false,
    };
  },
  methods: {
    matchPassword(password, confirmPassword) {
      if (this.newPassword !== this.confirmNewPassword) {
        return false;
      } else {
        return true;
      }
    },
    checkPassword(password) {
      if (this.newPassword.length >= 8) {
        this.passwordLength = true;
      } else {
        this.passwordLength = false;
      }
      if (this.newPassword.match(/[A-Z]/)) {
        this.passwordCapital = true;
      } else {
        this.passwordCapital = false;
      }
      if (this.newPassword.match(/[0-9]/)) {
        this.passwordNumber = true;
      } else {
        this.passwordNumber = false;
      }
      if (this.newPassword.match(/[a-z]/)) {
        this.passwordsmall = true;
      } else {
        this.passwordsmall = false;
      }
      if (
        this.passwordLength &&
        this.passwordCapital &&
        this.passwordNumber &&
        this.passwordsmall
      ) {
        this.passwordValid = true;
        return true;
      } else {
        this.passwordValid = false;
        return false;
      }
    },
    changeStateSignup() {
      this.$emit("changeState", "signUpForm");
    },
    changeStateLogin() {
      this.$emit("changeState", "loginForm");
    },
    changePass(event) {
      if (
        !this.checkPassword(this.newPassword) ||
        !this.matchPassword(this.newPassword, this.confirmNewPassword)
      ) {
        event.preventDefault();
      } else {
        fetch(`http://localhost:8080/updatePassword/${this.email}`, {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(this.newPassword),
        })
          .then((res) => {
            if (res.ok) {
              return res.json();
            } else if (res.status === 404) {
              throw new Error("User not found");
            } else {
              throw new Error(`Error: ${res.status}`);
            }
          })
          .then((updatedUser) => {
            console.log("Password updated:", updatedUser);
          })
          .catch((error) => {
            console.error("Error during password update:", error);
          });
        this.changeStateLogin();
      }
    },
  },
};
</script>

<style scoped>
h3 {
  text-align: center;
}
.img {
  position: relative;
  width: 55%;
  top: -130px;
}

input {
  margin-right: 5px;
  display: block;
  padding: 4px 2px;
  width: 100%;
  border: none;
  border-bottom: 2px solid #ddd;
}
h2 {
  text-align: start;
  margin-bottom: 20px;
  cursor: pointer;
}
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
.form {
  position: absolute;
  border-radius: 40px;
  padding: 50px 50px 60px 50px;
  width: 400px;
  background-color: #e0dbdb;
  text-align: start;
  right: 5%;
  border-bottom: 5px solid #9c9c9c;
  top: 20px;
}
.butn {
  background-color: #bf7934;
  border-radius: 50px;
  margin-top: 30px;
  padding: 7px;
  border: 0px solid transparent;
}
.butn:hover {
  background-color: white;
  color: #bf7934;
}
label {
  margin: 25px 0px 15px;
  font-size: 16px;
  letter-spacing: 1px;
  font-weight: bold;
}
section {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 600px;
}

h6 {
  direction: rtl;
  cursor: pointer;
  padding: 20px;
  text-decoration: underline;
}
.alert {
  margin-top: 10px;
  padding: 7px 10px;
}
h5 {
  color: red;
  font-size: 14px;
  margin-top: 5px;
}
</style>