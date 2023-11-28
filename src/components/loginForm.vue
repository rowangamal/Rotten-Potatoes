<template>
  <div class="container">
    <section>
        <div class="img">
            <img src="..\assets\image 2.png" alt="">
        </div>
    </section>
  </div>
</template>

<script>
export default {

}
</script>

<style>
h3 {
  text-align: center;
}
.img {
    position: relative;
  width: 65%;
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
  padding: 50px;
  max-width: 400px;
  background-color: #d9d9d9;
  text-align: start;
  right: 5%;
  margin-top: 50px;
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

</style>