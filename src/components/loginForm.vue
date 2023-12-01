<template>
  <div class="container">
    <section>
      <div class="img">
        <img src="..\assets\image 2.png" alt="" />
      </div>
      <div class="form">
        <h2 @click="changeStateBack">
          <i class="fa-solid fa-arrow-left" style="color: #000000"></i>
        </h2>
        <h3>Hi Again!</h3>
        <form>
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
            </div>
            <h6 @click="changeStateForget"> Forget Your Password</h6>
          </div>
          <div class="row">
            <button @click="logIn" class="butn">Login</button>
          </div>
          <div class="row">
            <h5>Or login with</h5>
          </div>
          <div class="row icons justify-content-center">
            <div class="col-4">
              <button class="shortcut">
                <img src="..\assets\Google button.png" alt="" />
              </button>
            </div>
            <div class="col-4">
              <button class="shortcut">
                <img src="..\assets\Facebook button.png" alt="" />
              </button>
            </div>
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

export default {
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
    logIn() {
      let found = false;
      const fakeToken = "your-fake-token";
      fetch("http://localhost:8000/usersData")
        .then((res) => res.json())
        .then((data) => {
          for (let i = 0; i < data.length; i++) {
            if (
              data[i].email === this.email &&
              data[i].password === this.password
            ) {
              found = true;
            }
          }
          if (found) {
            localStorage.setItem("token", fakeToken);
            alert("User found");
            this.$emit("changeState", "");
            this.$emit("login", true);
          } else {
            alert("User not found");
          }
        })
        .catch((error) => {
          console.error("Error during login:", error);
          alert("An error occurred during login");
        });
    },
    checkLoggedIn() {
      const token = localStorage.getItem("token");
      if (token) {
        this.$emit("changeState", "");
        this.$emit("login", true);
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
h5 {
  text-align: center;
  margin-top: 20px;
  font-size: 14px;
  color: #000;
  position: relative;
  display: inline-block;
}
h5::before,
h5::after {
  content: "";
  position: absolute;
  top: 50%;
  width: 50%;
  height: 1px;
  background-color: black;
}

h5::before {
  left: 0;
  transform: translateX(-30%);
}

h5::after {
  right: 0;
  transform: translateX(30%);
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
</style>