<template>
  <div class="container">
    <section>
      <div class="img">
        <img src="..\assets\image 3.png" alt="" />
      </div>
      <div class="form">
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
              <input type="password" required v-model="password" />
            </div>
          </div>
          <div class="row">
            <div class="col">
              <label>Confirm password</label>
              <input type="password" required v-model="confirmPassword" />
            </div>
          </div>
          <div class="row justify-content-between">
            <label>Birthday date</label>
            <div class="col-4">
              <input type="text" placeholder="DD" v-model="day" />
            </div>
            <div class="col-4">
              <input type="text" placeholder="MM" v-model="month" />
            </div>
            <div class="col-4">
              <input type="text" placeholder="YYYY" v-model="year" />
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
            <button class="btn butn">Submit</button>
          </div>
        </form>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      firstName: "",
      lastName: "",
      email: "",
      username: "",
      password: "",
      confirmPassword: "",
      year: "",
      month: "",
      day: "",
      gender: "",
    };
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
  max-width: 400px;
  background-color: #d9d9d9;
  text-align: start;
  right: 5%;
  border-bottom: 5px solid #9c9c9c;
}
.butn {
  background-color: #ee9e3f;
  border-radius: 50px;
  margin-top: 30px;
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
  padding: 5px 3px;
  width: 100%;
  border: none;
  border-bottom: 2px solid #ddd;
}
select {
  margin-bottom: 20px;
}

.img {
  width: 65%;
}

h3 {
  text-align: center;
}
 section {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 60px;
  height: 1000px;
} 
</style>