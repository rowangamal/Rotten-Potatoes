<template>
  <div class="card" @click="openmoviepage">
    <div class="img">
      <div class="layer"></div>
      <img
        :src="imagee"
        alt=""
        @load="loaded = true"
        @error="handleImageError"
      />
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
import $store from "../store/index.js";

export default {
  props: ["img", "title", "date", "rate", "id"],
  data() {
    return {
      defaultImage:
        "data:image/png;base64,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",
      loaded: false,
    };
  },
  methods: {
    openmoviepage() {
      $store.commit("setsearchMovs", []);
      this.$router.push({ name: "movie", params: { id: this.id } });
      
    },
    handleImageError() {
      this.loaded = true;
    },
  },
  computed: {
    imagee() {
      return this.loaded ? this.img : this.defaultImage;
    },
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
  word-wrap: break-word;
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
  bottom: 270px;
  left: -69px;
}
.img {
  border-radius: 10px;
  overflow: hidden;
  padding: 0px;
  border: none;
}
.info {
  position: relative;
  padding: 10px;
  padding-top: 0px;
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
  height: 100%;
  width: 100%;
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
</style>