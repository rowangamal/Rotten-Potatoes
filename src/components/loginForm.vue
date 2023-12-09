<template>
  <div class="container">
    <section>
      <div class="img">
        <img src="..\assets\image 2.png" alt="" />
      </div>
      <div class="form">
        <form @submit="logIn">
          <h2 @click="changeStateBack">
            <i class="fa-solid fa-arrow-left" style="color: #000000"></i>
          </h2>
          <h3>Hi Again!</h3>
          <div
            v-show="this.missingData"
            class="alert alert-danger"
            role="alert"
          >
            <h5>Some Data Is Missing</h5>
          </div>
          <div class="row">
            <div class="col">
              <label>Email</label>
              <input type="email" required v-model="email" />
            </div>
          </div>
          <div class="row">
            <div class="col">
              <label>Password</label>
              <input type="password" required v-model="password" />
              <div
                v-show="this.userNotFound"
                class="alert alert-danger"
                role="alert"
              >
                <h5>Wrong Email Or Paassword</h5>
              </div>
            </div>
            <h6 @click="changeStateForget">Forget Your Password</h6>
          </div>
          <div class="row">
            <button class="butn">Login</button>
          </div>

          <div class="row">
            <h6 class="center" @click="changeStateSignup">
              Don't have an account? Sign up
            </h6>
          </div>
        </form>
      </div>
    </section>
  </div>
</template>

<script>
import { storeID } from "./id.js";
export default {
  data() {
    return {
      email: "",
      password: "",
      userNotFound: false,
      USER: {},
      missingData: false,
    };
  },
  created() {
    this.checkLoggedIn();
  },
  methods: {
    changeStateSignup() {
      this.$emit("changeState", "signUpForm");
    },
    changeStateBack() {
      this.$emit("changeState", "");
    },
    changeStateForget() {
      this.$emit("changeState", "forgetPassword");
    },
    logIn(event) {
      this.checkMissingData();
      this.userNotFound = false;
      const email = this.email;
      const password = this.password;
      const apiUrl = `http://localhost:8080/checkPass/${email}`;

      fetch(apiUrl, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(password),
      })
        .then((response) => {
          if (response.ok) {
            return response.json();
          } else if (response.status === 404) {
            return null;
          } else {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
        })
        .then((userData) => {
          if (userData !== null) {
            console.log("Login successful");
            const fakeToken = "your-fake-token";
            const userDataString = JSON.stringify(userData);
            localStorage.setItem("userData", userDataString);
            localStorage.setItem("token", fakeToken);
            storeID.currUser = userData;
            this.$emit("changeStateLogin", "", userData);
            this.$emit("login", true);
          } else {
            this.userNotFound = true;
            event.preventDefault();
          }
        })
        .catch((error) => {
          console.error("Error during login:", error);
        });

      // fetch(`http://localhost:8080/users/${email}`)
      //   .then((res) => {
      //     if (res.ok) {
      //       return res.json();
      //     } else if (res.status === 404) {
      //       throw new Error("User not found");
      //     } else {
      //       throw new Error(`Error: ${res.status}`);
      //     }
      //   })
      //   .then((user) => {
      //     console.log(user);
      //     if (user.password === password) {
      //       const fakeToken = "your-fake-token";
      //       localStorage.setItem("token", fakeToken);
      //       storeID.currUser = user;
      //       this.$emit("changeState", "");
      //       this.$emit("login", true);
      //     } else {
      //       this.userNotFound = true;
      //       event.preventDefault();
      //     }
      //   })
      //   .catch((error) => {
      //     console.error("Error during login:", error);
      //   });
    },
    checkLoggedIn() {
      const token = localStorage.getItem("token");
      if (token) {
        const userDataString = localStorage.getItem("userData");
        const userData = JSON.parse(userDataString);
        this.$emit("changeStateLogin", "", userData);
        this.$emit("login", true);
      }
    },
    checkMissingData() {
      if (this.email.length == 0 || this.password.length == 0) {
        this.missingData = true;
      } else {
        this.missingData = false;
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

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
h2 {
  text-align: start;
  margin-bottom: 20px;
  cursor: pointer;
}
.form {
  position: absolute;
  border-radius: 40px;
  padding: 60px 80px 40px 80px;
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
  padding: 8px;
  border: 0px solid transparent;
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
h4 {
  text-align: center;
  margin-top: 20px;
  font-size: 14px;
  color: #000;
  position: relative;
  display: inline-block;
}
h4::before,
h4::after {
  content: "";
  position: absolute;
  top: 50%;
  width: 50%;
  height: 1px;
  background-color: black;
}

h4::before {
  left: 0;
  transform: translateX(-40%);
}

h4::after {
  right: 0;
  transform: translateX(40%);
}

.icons {
  margin-top: 20px;
}
.shortcut {
  background-color: #fff;
  border: 1px solid #000;
  border-radius: 50%;
  cursor: pointer;
  border: none;
}
.center {
  margin: 0 auto;
  cursor: pointer;
  text-align: center;
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
