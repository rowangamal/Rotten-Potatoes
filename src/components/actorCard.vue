<template>
  <div class="card">
    <div class="img">
      <div class="layer"></div>
      <img :src="imagee" alt="" />
    </div>
    <div class="info">
      <h2>{{ name }}</h2>
      <p>{{ character }}</p>
    </div>
  </div>
</template>

<script>
export default {
  props: ["name", "character", "image"],
  data() {
    return {
      imagee: "https://image.tmdb.org/t/p/original" + this.image,
    };
  },
  methods: {
    // openmoviepage(){
    //   storeID.ID=this.id;
    //   this.$emit("openmoviepage",this.id);
    // }
  },
};
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
h2 {
  font-size: 16px;
  font-weight: bold;
  margin: 0;
  padding: 0px;
}
p {
  font-size: 10px;
  margin: 0;
}
.img {
  margin-top: 5px;
  border-radius: 50%;
}
.info {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  background-color: rgba(255, 255, 255, 0.295);
  width: 70%;
  left: 17%;
}
.card:hover img {
  transform: scale(1.1);
  cursor: pointer;
}
.card .img {
  margin: auto;
  position: relative;
  overflow: hidden;
  background-color: transparent;
  width: 100px;
}

.card .img img {
  overflow: hidden;
  transition: all 0.5s ease-in-out;
  width: 100px;
}
.layer {
  position: absolute;
  inset: 0;
  background-color: rgba(0, 0, 0, 0.5);
  width: 100%;
  height: 100%;
  opacity: 0;
  transition: all 0.25s;
}
.card:hover .layer {
  opacity: 1;
  margin: auto;
  text-align: center;
  z-index: 1;
}

.card {
  background-color: transparent;
  border: none;
  margin: auto;
  padding: 0px;
}
</style>