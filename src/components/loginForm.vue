<template>
  <div class="container">
    <section>
      <div class="img">
        <img src="..\assets\image 2.png" alt="" />
      </div>
      <div class="form">
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
            <h6>Forget Your Password</h6>
          </div>
          <div class="row">
            <button @click="logIn" class="butn">Login</button>
          </div>
        </form>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  methods:{
    logIn(){
      let found=false;
      fetch('http://localhost:8000/usersData')
      .then(res=>{
        return res.json();
      }).then(data=>{
        for(let i=0;i<data.length;i++){
          if(data[i].email===this.email && data[i].password===this.password){
            found=true;
          }
        }

      if(found){
        alert('user found');
      }
      else{
        alert('user is not found');
      }
      });
      
    }
  }
};
</script>

<style>
h3 {
  text-align: center;
}
.img {
  position: relative;
  width: 55%;
  top: -130px;
}

input,
select {
  margin-right: 5px;
  display: block;
  padding: 8px 4px;
  width: 100%;
  border: none;
  border-bottom: 2px solid #ddd;
}
select {
  margin-bottom: 20px;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
.form {
  position: absolute;
  border-radius: 40px;
  padding: 80px;
  width: 400px;
  background-color: #e0dbdb;
  text-align: start;
  right: 5%;
  margin-top: 50px;
  border-bottom: 5px solid #9c9c9c;
  top: 30px;
}
.butn {
  background-color: #BF7934;
  border-radius: 50px;
  margin-top: 30px;
  padding: 10px;
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

h6{
    direction: rtl;
    cursor: pointer;
    padding: 20px;
    text-decoration: underline;
}
</style>