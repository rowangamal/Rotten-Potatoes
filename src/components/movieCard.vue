<template>
  <div class="card" @click="openmoviepage">
    <div class="img">
        <div class="layer"></div>
      <img :src="img" alt="" />
    </div>
    <div class="info">
      <div class="rate">
        <p>{{ rate }}</p>
      </div>
      <h2>{{ title }}</h2>
      <p>{{ date }}</p>

    </div>
  </div>
</template>

<script>
export default {
  props: ["img", "title", "date", "rate","id" ],
  methods: {
    openmoviepage(){
      this.$emit("openMovie","MoviePage",this.id);
    }
  }
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
  word-wrap: break-word ;
  word-break: break-all;
  white-space: normal;
}
p {
  font-size: 10px;
  margin: 0;
}
.rate {
  background-color: #ef9e3f;
  width: 30px;
  height: 30px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  bottom: 260px;
  left: -59px;
}
.img {
  margin-top: 5px;
  border-radius: 10px;
}
.info {
  position: relative;
  padding: 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
}
.card:hover img {
  transform: scale(1.1);
  cursor: pointer;
}
.card .img {
    position: relative;
    overflow: hidden;
}

.card .img img {
  overflow: hidden;
  transition: all 0.5s ease-in-out;
  height: 259.3px;
  width: 172.8px;
}
.layer{
    position: absolute;
    inset: 0;
    background-color: rgba(0, 0, 0, 0.5);
    width: 100%;
    height: 100%;
    opacity: 0;
    transition: all 0.25s;
}
.card:hover .layer{
    opacity: 1;
    margin: auto;
    text-align: center;
    z-index: 1;
}
</style>