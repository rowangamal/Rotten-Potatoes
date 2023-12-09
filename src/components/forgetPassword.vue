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
        <h3>Let's Get There!</h3>
        <div class="row">
          <div class="col">
            <label>Email</label>
            <input type="email" required v-model="email" />
          </div>
          <div v-show="err" class="alert alert-danger" role="alert">
            <h5>User Not Found!</h5>
          </div>
        </div>
        <div class="row">
          <button class="butn" @click="changeStateSecurity">
            ForgetPassword
          </button>
        </div>
        <div class="row">
          <h6 class="center" @click="changeStateSignup">
            Don't have an account? Sign up
          </h6>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: "",
      question: "",
      answer: "",
      err: false,
    };
  },
  methods: {
    changeStateSignup() {
      this.$emit("changeState", "signUpForm");
    },
    changeStateLogin() {
      this.$emit("changeState", "loginForm");
    },
    changeStateSecurity(event) {
    this.err = false;
      const email = this.email;
      console.log(email);
      fetch(`http://localhost:8080/users/${email}`)
        .then((res) => {
          if (res.ok) {
            return res.json();
          } else if (res.status === 404) {
            throw new Error("User not found");
          } else {
            throw new Error(`Error: ${res.status}`);
          }
        })
        .then((user) => {
          console.log(user);
          if (user != null) {
            this.question = user.securityQuestion;
            this.answer = user.securityAnswer;
            this.$emit(
              "changeStateSecurity",
              "securityForm",
              this.email,
              this.question,
              this.answer
            );
          } else {
            this.err = true;
            event.preventDefault();
            console.log("User not found");
          }
        })
        .catch((error) => {
          this.err = true;
          event.preventDefault();
          console.log(error);
        });
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
.alert {
  margin-top: 10px;
  padding: 3px 5px;
}
h5 {
  position: relative;
  color: red;
  font-size: 14px;
  margin-top: 5px;
  margin-bottom: 5px;
  left: 10px;
}
</style>