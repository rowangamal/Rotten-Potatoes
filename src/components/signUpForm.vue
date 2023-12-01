<template>
  <div class="container">
    <section>
      <div class="img">
        <img src="..\assets\image 3.png" alt="" />
      </div>
      <div class="form">
        <h2 @click="changeStateBack">
          <i class="fa-solid fa-arrow-left" style="color: #000000"></i>
        </h2>
        <h3>New member? Welcome!</h3>
        <form>
          <div class="row justify-content-between">
            <div class="col-6">
              <label>First name</label>
              <input type="text" required v-model="firstName" />
            </div>
            <div class="col-6">
              <label>Last name</label>
              <input type="text" required v-model="lastName" />
            </div>
          </div>
          <div class="row">
            <div class="col">
              <label>Username</label>
              <input type="text" required v-model="username" />
            </div>
          </div>
          <div class="row">
            <div class="col">
              <label>Email</label>
              <input type="email" required v-model="email" />
            </div>
          </div>
          <div class="row">
            <div class="col">
              <label>Create password</label>
              <input
                type="password"
                required
                v-model="password"
                @input="checkPassword(this.password)"
              />
              <div
                v-show="!passwordValid && password.length > 0"
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
              <label>Confirm password</label>
              <input
                type="password"
                required
                v-model="confirmPassword"
                @blur="matchPassword(this.password, this.confirmPassword)"
              />
              <div
                v-show="
                  !matchPassword(this.password, this.confirmPassword) &&
                  confirmPassword.length > 0
                "
                class="alert alert-danger"
                role="alert"
              >
                <h5>Password Doesn't Match</h5>
              </div>
            </div>
          </div>
          <div class="row">
            <label>Security Question</label>
            <select v-model="securityQuestion" required>
              <option class="selectOpt" value="pet">
                What was the name of your first pet?
              </option>
              <option class="selectOpt" value="city">
                In which city were you born?
              </option>
              <option class="selectOpt" value="favorite-book">
                What is your favorite book or movie?
              </option>
              <option class="selectOpt" value="childhood">
                What is the name of your favorite childhood friend?
              </option>
              <option class="selectOpt" value="graduate">
                In which year did you graduate from high school or college?
              </option>
            </select>
          </div>
          <div v-show="securityQuestion" class="row">
            <div class="col">
              <label>Answer</label>
              <input type="text" required v-model="securityAnswer" />
            </div>
          </div>
          <div class="row justify-content-between">
            <label>Birthday date</label>
            <div class="col-4">
              <input
                type="text"
                placeholder="DD"
                v-model="day"
                @input="formatDatePart('day')"
              />
            </div>
            <div class="col-4">
              <input
                type="text"
                placeholder="MM"
                v-model="month"
                @input="formatDatePart('month')"
              />
            </div>
            <div class="col-4">
              <input
                type="text"
                placeholder="YYYY"
                v-model="year"
                @input="formatDatePart('year')"
              />
            </div>
          </div>
          <div class="row">
            <label>Gender</label>
            <select v-model="gender" required>
              <option class="selectOpt" value="male">Male</option>
              <option class="selectOpt" value="female">female</option>
            </select>
          </div>
          <div class="row">
            <button @click="signUp" class="btn butn">Submit</button>
          </div>
          <div class="row">
            <h6 class="center" @click="changeStateLogin">
              Already have an account? Login
            </h6>
          </div>
        </form>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  props: ["changeState"],
  data() {
    return {
      firstName: "",
      lastName: "",
      email: "",
      username: "",
      password: "",
      confirmPassword: "",
      securityQuestion: "",
      securityAnswer: "",
      year: "",
      month: "",
      day: "",
      gender: "",
      passwordLength: false,
      passwordCapital: false,
      passwordNumber: false,
      passwordsmall: false,
      passwordValid: false,
    };
  },
  methods: {
    changeStateLogin() {
      this.$emit("changeState", "loginForm");
    },
    formatDatePart(part) {
      let formattedValue = "";

      switch (part) {
        case "day":
          formattedValue = this.day.replace(/\D/g, "").slice(0, 2);
          this.day =
            formattedValue && formattedValue <= 31 ? formattedValue : "";
          break;

        case "month":
          formattedValue = this.month.replace(/\D/g, "").slice(0, 2);
          this.month =
            formattedValue && formattedValue <= 12 ? formattedValue : "";
          break;

        case "year":
          formattedValue = this.year.replace(/\D/g, "").slice(0, 4);
          this.year = formattedValue;
          break;

        default:
          break;
      }
    },
    matchPassword(password, confirmPassword) {
      if (this.password !== this.confirmPassword) {
        return false;
      } else {
        return true;
      }
    },
    checkPassword(password) {
      if (this.password.length >= 8) {
        this.passwordLength = true;
      } else {
        this.passwordLength = false;
      }
      if (this.password.match(/[A-Z]/)) {
        this.passwordCapital = true;
      } else {
        this.passwordCapital = false;
      }
      if (this.password.match(/[0-9]/)) {
        this.passwordNumber = true;
      } else {
        this.passwordNumber = false;
      }
      if (this.password.match(/[a-z]/)) {
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
    changeStateBack() {
      this.$emit("changeState", "");
    },
    signUp(event) {
      if (
        !this.checkPassword(this.password) ||
        !this.matchPassword(this.password, this.confirmPassword)
      ) {
        event.preventDefault();
      } else {
        let userObj = {
          email: this.email,
          password: this.password,
          securityQuestion: this.securityQuestion,
          securityAnswer: this.securityAnswer,
          favourites: [],
          watchlist: [],
          firstName: this.firstName,
          lastName: this.lastName,
          userName: this.userName,
          gender: this.gender,
        };
        console.log(userObj);
        fetch("http://localhost:8000/usersData", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(userObj),
        }).then(() => {
          console.log("done");
          this.$emit("changeState", "loginForm");
        }).catch((error) => {
          console.error("Error during signup:", error);
        });
      }
    },
  },
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
.form {
  position: absolute;
  border-radius: 40px;
  padding: 50px;
  max-width: 380px;
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
label {
  margin: 25px 0px 15px;
  font-size: 16px;
  letter-spacing: 1px;
  font-weight: bold;
}
input,
select {
  margin-right: 5px;
  display: block;
  padding: 2px 1px;
  width: 100%;
  border: none;
  border-bottom: 2px solid #ddd;
}

select {
  width: 92%;
  margin-left: 12px;
}

.img {
  width: 65%;
  position: relative;
  top: -130px;
}

h3 {
  text-align: center;
}
h2 {
  text-align: start;
  margin-bottom: 20px;
  cursor: pointer;
}
section {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 60px;
  height: 1000px;
}
h6 {
  direction: rtl;
  cursor: pointer;
  padding: 20px;
  text-decoration: underline;
}
.center {
  margin: 0 auto;
  cursor: pointer;
  text-align: center;
}
</style>