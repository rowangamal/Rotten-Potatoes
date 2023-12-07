<template>
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
        <h2 @click="changeStateBack">
          <i class="fa-solid fa-arrow-left" style="color: #000000"></i>
        </h2>
        <h3>New member? Welcome!</h3>
        <div
          v-show="this.userDuplicate"
          class="alert alert-danger"
          role="alert"
        >
          <h5>User Already Exist</h5>
        </div>
        <div class="row justify-content-between">
          <div class="col-6">
            <label>First name</label>
            <input type="text" placeholder="First name" v-model="firstName" />
          </div>
          <div class="col-6">
            <label>Last name</label>
            <input type="text" placeholder="Last name" v-model="lastName" />
          </div>
        </div>
        <div class="row">
          <div class="col">
            <label>Username</label>
            <input
              type="text"
              placeholder="Enter your username"
              v-model="username"
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
          <div class="check">
            <input
              type="checkbox"
              id="myCheckbox"
              v-model="isChecked"
              @click="termsDisplay = true"
            />
            <label for="myCheckbox">Check terms and conditions</label>
            <div class="popup" v-show="isChecked & termsDisplay">
              <div id="terms-and-conditions">
                <h1>Potatoes and Conditions</h1>
                <br />
                <ol>
                  <li>
                    <strong>Acceptance of Terms</strong>
                    <ul>
                      <li>
                        By accessing or using Rotten Potatoes, you agree to
                        comply with and be bound by these Terms and Conditions
                        of Use. If you do not agree with any part of these
                        terms, you may not access the website.
                      </li>
                    </ul>
                  </li>

                  <li>
                    <strong>User Accounts</strong>
                    <ul>
                      <li>
                        Users may be required to create an account to access
                        certain features of the website. You are responsible for
                        maintaining the confidentiality of your account and
                        password.
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
                        Rotten Potatoes contains text, images, videos, and other
                        content protected by intellectual property laws. You may
                        not modify, publish, transmit, distribute, perform, or
                        create derivative works from any content.
                      </li>
                      <li>
                        Users are encouraged to submit content (e.g., reviews,
                        comments) but grant [Your Website] a non-exclusive,
                        worldwide, royalty-free license to use, reproduce,
                        modify, publish, and distribute such content.
                      </li>
                    </ul>
                  </li>

                  <li>
                    <strong>Privacy</strong>
                    <ul>
                      <li>
                        Rotten Potatoes values user privacy. Our Privacy Policy
                        outlines the types of information collected, how it is
                        used, and how it is protected. By using the website, you
                        consent to the terms of the Privacy Policy.
                      </li>
                    </ul>
                  </li>
                  <li>
                    <strong>User Conduct</strong>
                    <ul>
                      <li>
                        Users agree not to engage in any conduct that restricts
                        or inhibits any other user from using or enjoying [Your
                        Website].
                      </li>
                      <li>
                        Users may not use the website for any unlawful purpose
                        or in violation of any applicable laws.
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
                        Rotten Potatoes does not warrant that the website will
                        be error-free, secure, or continuously available.
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
          <button @click="signUp" class="btn butn">Submit</button>
        </div>
        <div class="row">
          <h6 class="center" @click="changeStateLogin">
            Already have an account? Login
          </h6>
        </div>
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
      isChecked: false,
      termsDisplay: false,
      submitState: false,
      userDuplicate: false,
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
    this.submitState=!this.submitState
      if (
        !this.checkPassword(this.password) ||
        !this.matchPassword(this.password, this.confirmPassword) ||
        !this.isChecked
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
        console.log(userObj);
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
            console.log(user)
            for (let i = 0; i < user.length; i++) {
              if (
                user[i].email === this.email ||
                user[i].userName === this.username
              ) {
                this.userDuplicate = true;
                break;
              }
              console.log(user[i].email,user[i].userName);
            }
          })
        .then(() => {
        if(!this.userDuplicate){
        fetch("http://localhost:8080/users/add", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(userObj),
        })
          .then(() => {
            console.log("done");
            this.$emit("changeState", "loginForm");
          })
          .catch((error) => {
            console.error("Error during signup:", error);
          });
        }
      }).catch((error) => {
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
</style>