<template>
  <div class="all">
    <div class="container">
      <div
        class="overlay"
        v-show="isChecked & termsDisplay"
        @click="termsDisplay = !termsDisplay"
      ></div>
      <!--for popup-->
      <section>
        <div class="img">
          <img src="..\assets\image 3.png" alt="" />
        </div>
        <div class="form">
          <form @submit.prevent="signUp">
            <router-link to="/" class="center">
              <h2>
                <i class="fa-solid fa-arrow-left" style="color: #000000"></i>
              </h2>
            </router-link>
            <h3>New member? Welcome!</h3>
            <div
              v-show="this.userDuplicate"
              class="alert alert-danger"
              role="alert"
            >
              <h5>User Already Exist</h5>
            </div>
            <div
              v-show="this.missingData"
              class="alert alert-danger"
              role="alert"
            >
              <h5>Some Data Is Missing</h5>
            </div>
            <div class="row justify-content-between">
              <div class="col-6">
                <label>First name</label>
                <input
                  type="text"
                  required
                  placeholder="First name"
                  v-model="firstName"
                />
              </div>
              <div class="col-6">
                <label>Last name</label>
                <input
                  type="text"
                  required
                  placeholder="Last name"
                  v-model="lastName"
                />
              </div>
            </div>
            <div class="row">
              <div class="col">
                <label>Username</label>
                <input
                  type="text"
                  placeholder="Enter your username"
                  v-model="username"
                  required
                />
              </div>
            </div>
            <div class="row">
              <div class="col">
                <label>Email</label>
                <input
                  type="email"
                  placeholder="Enter your email"
                  v-model="email"
                  required
                />
              </div>
            </div>
            <div class="row">
              <div class="col">
                <label>Create password</label>
                <input
                  type="password"
                  placeholder="Enter your password"
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
                    Password Should Contain Capital Letter,Small Letter and
                    Number and over 8 lentgh
                  </h5>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col">
                <label>Confirm password</label>
                <input
                  type="password"
                  placeholder="Confirm your password"
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
                <option
                  class="selectOpt"
                  value="What was the name of your first pet?"
                >
                  What was the name of your first pet?
                </option>
                <option class="selectOpt" value="In which city were you born?">
                  In which city were you born?
                </option>
                <option
                  class="selectOpt"
                  value="What is your favorite book or movie?"
                >
                  What is your favorite book or movie?
                </option>
                <option
                  class="selectOpt"
                  value="What is the name of your favorite childhood friend?"
                >
                  What is the name of your favorite childhood friend?
                </option>
                <option
                  class="selectOpt"
                  value="In which year did you graduate from high school or college?"
                >
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
                  type="number"
                  placeholder="YYYY"
                  v-model="year"
                  @input="formatDatePart('year')"
                />
              </div>
              <div
              v-show="this.wrongAge"
              class="alert alert-danger"
              role="alert"
            >
              <h5>Invalid Age</h5>
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
              <div class="check">
                <input
                  type="checkbox"
                  id="myCheckbox"
                  v-model="isChecked"
                  @click="termsDisplay = true"
                />
                <label for="myCheckbox">Check terms and conditions</label>
                <div class="popup" v-show="isChecked & termsDisplay">
                  <h4 @click="termsDisplay = false">X</h4>
                  <div id="terms-and-conditions">
                    <h1>Potatoes and Conditions</h1>
                    <br />
                    <ol>
                      <li>
                        <strong>Acceptance of Terms</strong>
                        <ul>
                          <li>
                            By accessing or using Rotten Potatoes, you agree to
                            comply with and be bound by these Terms and
                            Conditions of Use. If you do not agree with any part
                            of these terms, you may not access the website.
                          </li>
                        </ul>
                      </li>

                      <li>
                        <strong>User Accounts</strong>
                        <ul>
                          <li>
                            Users may be required to create an account to access
                            certain features of the website. You are responsible
                            for maintaining the confidentiality of your account
                            and password.
                          </li>
                          <li>
                            You agree to provide accurate, current, and complete
                            information during the registration process.
                          </li>
                        </ul>
                      </li>

                      <li>
                        <strong>Content and Intellectual Property</strong>
                        <ul>
                          <li>
                            Rotten Potatoes contains text, images, videos, and
                            other content protected by intellectual property
                            laws. You may not modify, publish, transmit,
                            distribute, perform, or create derivative works from
                            any content.
                          </li>
                          <li>
                            Users are encouraged to submit content (e.g.,
                            reviews, comments) but grant [Your Website] a
                            non-exclusive, worldwide, royalty-free license to
                            use, reproduce, modify, publish, and distribute such
                            content.
                          </li>
                        </ul>
                      </li>

                      <li>
                        <strong>Privacy</strong>
                        <ul>
                          <li>
                            Rotten Potatoes values user privacy. Our Privacy
                            Policy outlines the types of information collected,
                            how it is used, and how it is protected. By using
                            the website, you consent to the terms of the Privacy
                            Policy.
                          </li>
                        </ul>
                      </li>
                      <li>
                        <strong>User Conduct</strong>
                        <ul>
                          <li>
                            Users agree not to engage in any conduct that
                            restricts or inhibits any other user from using or
                            enjoying [Your Website].
                          </li>
                          <li>
                            Users may not use the website for any unlawful
                            purpose or in violation of any applicable laws.
                          </li>
                        </ul>
                      </li>
                      <li>
                        <strong>Disclaimer of Warranties</strong>
                        <ul>
                          <li>
                            Rotten Potatoes is provided "as is" without any
                            representations or warranties, express or implied.
                          </li>
                          <li>
                            Rotten Potatoes does not warrant that the website
                            will be error-free, secure, or continuously
                            available.
                          </li>
                        </ul>
                      </li>
                    </ol>
                  </div>
                </div>
              </div>
              <div
                v-show="!isChecked && submitState"
                class="alert alert-danger"
                role="alert"
              >
                <h5>Accepting terms and conditions is fundamental</h5>
              </div>
            </div>
            <div class="row">
              <button @click="checkMissing" class="btn butn">Submit</button>
            </div>
            <div class="row">
              <router-link to="/login" class="center">
                <h6>Already have an account? Login</h6>
              </router-link>
            </div>
          </form>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import $store from "../store/index.js";

export default {
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
      isChecked: false,
      termsDisplay: false,
      submitState: false,
      userDuplicate: false,
      missingData: false,
      wrongAge: false,
    };
  },

  methods: {
    formatDatePart(part) {
      let formattedValue = "";

      const today = new Date();
      const currentYear = today.getFullYear() - 11;
      const currentMonth = today.getMonth() + 1;
      const currentDay = today.getDate();

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
          this.checkAge();
          this.year=this.year.toString();
          
          formattedValue = this.year.replace(/\D/g, "").slice(0, 4);
          const minYear = 1900;
          this.year =
            formattedValue && formattedValue >= minYear
              ? formattedValue
              : this.year;
          if (this.year.length == 4) {
            if (parseInt(this.year, 10) < minYear) {
              this.year = 1900;
            } else if (parseInt(this.year, 10) > currentYear) {
              this.year = currentYear;
            }
          }
          break;

        default:
          break;
      }
      const selectedMonth = parseInt(this.month, 10);
      if (selectedMonth === 2) {
        const selectedYear = parseInt(this.year, 10);
        const isLeapYear =
          (selectedYear % 4 === 0 && selectedYear % 100 !== 0) ||
          selectedYear % 400 === 0;

        const maxDays = isLeapYear ? 29 : 28;
        this.day = this.day && this.day <= maxDays ? this.day : "";
      }
      if (
        this.year == currentYear &&
        this.month == currentMonth &&
        this.day > currentDay
      ) {
        this.day = "";
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
    checkAge(){
      if(this.year.length<4||
        this.month.length==0||
        this.day.length==0){
          this.wrongAge = true;
        }
        else{
          this.wrongAge = false;
        }
        let nums=["+","-",".","/"]
      for(let i=0;i<this.year.length;i++){
        if(nums.includes(this.year.charAt(i))){
          this.wrongAge=true;
        }
        
      }
    },
    checkMissing() {
      if (
        this.firstName.length == 0 ||
        this.lastName.length == 0 ||
        this.email.length == 0 ||
        this.username.length == 0 ||
        this.password.length == 0 ||
        this.confirmPassword.length == 0 ||
        this.securityQuestion.length == 0 ||
        this.securityAnswer.length == 0 ||
        this.gender.length == 0
        // this.year.length<4||
        // this.month.length==0||
        // this.day.length==0
      ) {
        scroll(0, 0);
        this.missingData = true;
      } else {
        this.missingData = false;
      }
    },
    signUp(event) {
      this.checkMissing();
      
      this.checkAge();
      this.submitState = !this.submitState;
      this.userDuplicate = false;
      if (
        !this.checkPassword(this.password) ||
        !this.matchPassword(this.password, this.confirmPassword) ||
        !this.isChecked||this.year.length<4|| this.wrongAge
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
          userName: this.username,
          gender: this.gender,
        };
        fetch(`http://localhost:8080/users`)
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
            for (let i = 0; i < user.length; i++) {
              if (
                user[i].email === this.email ||
                user[i].userName === this.username
              ) {
                this.userDuplicate = true;
                break;
              }
              else{
                this.userDuplicate = false;
              }
            }
          })
          .then(() => {
            if (!this.userDuplicate) {
              fetch("http://localhost:8080/users/add", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(userObj),
              })
                .then(() => {
                  console.log("done");
                  this.$router.push("/login");
                })
                .catch((error) => {
                  console.error("Error during signup:", error);
                });
            }
            else{
              window.scroll(0,0)
            }
          })
          .catch((error) => {
            console.error("Error during Signup:", error);
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
  margin-top: 20px;
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
  height: 1200px;
}
h6 {
  direction: rtl;
  cursor: pointer;
  padding: 20px;
  text-decoration: underline;
}
h4 {
  cursor: pointer;
  direction: rtl;
}
.center {
  margin: 0 auto;
  cursor: pointer;
  text-align: center;
}
.check {
  display: flex;
  flex-flow: row;
  align-items: space-evenly;
}
#myCheckbox {
  margin-right: 10px;
  width: auto;
  height: auto;
}
.popup {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 700px;
  height: 600px;
  font: 13.5px/1.5 Helvetica, Verdana, sans-serif;
  padding: 20px;
  padding-left: 30px;
  background-color: #fff;
  /* background-image: url("../assets/potatoterms.png"); */
  background-size: cover;
  /* filter: blur(20px); */
  border: 1px solid #ccc;
  border-radius: 30px;
  z-index: 2;
}
.popup button {
  position: absolute;
  top: 10px;
  right: 14px;
  font-size: 42px;
  line-height: 18px;
  padding: 0;
  margin: 0;
  border: none;
  background: transparent;
  cursor: pointer;
  color: #333;
}
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.7); /* Semi-transparent grayish background */
  z-index: 1;
}
.alert {
  margin: 0;
}
a {
  font-weight: bold;
  color: black;
  text-decoration: none;
}

a.router-link-exact-active {
  color: black;
  text-decoration: none;
}
</style>