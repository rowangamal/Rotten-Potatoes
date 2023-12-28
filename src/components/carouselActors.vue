<template>
  <div class="scrollable-container">
    <div class="scrollable-row" ref="scrollContainer">
      <div class="row">
        <actorCard
          v-for="ac in actorss"
          :key="ac.id"
          :name="ac.name"
          :character="ac.character"
          :image="ac.profile_path"
          class="col-2"
        />
      </div>
    </div>
    <button @click="scrollLeft" class="scroll-button left-button" :style="{ backgroundColor: scrollLeftStatus ? '#ef9e3f' : '#bbb' }">
      <i class="fa-solid fa-caret-left"></i>
    </button>
    <button @click="scrollRight" class="scroll-button right-button" :style="{ backgroundColor: scrollRightStatus ? '#ef9e3f' : '#bbb' }">
      <i class="fa-solid fa-caret-right"></i>
    </button>
  </div>
</template>

<script>
import $store from "../store/index.js";

import actorCard from "./actorCard.vue";

export default {
  props: ["actor"],
  components: {
    actorCard,
  },
  data() {
    return {
      actorss: [],
      done: false,
      scrollStep: 260,
      scrollLeftStatus:false,
      scrollRightStatus:true,
    };
  },
  computed: {
    scrollContainer() {
      return this.$refs.scrollContainer;
    },
    canScrollRight() {
      return (
        this.scrollContainer &&
        this.scrollContainer.scrollLeft <
          this.scrollContainer.scrollWidth - this.scrollContainer.clientWidth
      );
    },
  },
  updated() {
    if ($store.state.currMov && !this.done) {
      this.actorss = $store.state.currMov;
      console.log(this.actorss);
      this.done = true;
    }
  },
  methods: {
    changeColor() {
      if(this.scrollContainer.scrollLeft===0){
        this.scrollLeftStatus=false;
        this.scrollRightStatus=true;
      }
      else if(Math.ceil(this.scrollContainer.scrollLeft)-this.scrollContainer.scrollWidth-this.scrollContainer.clientWidth>-1900 ){
        this.scrollLeftStatus=true;
        this.scrollRightStatus=false;
      }
      else{
        this.scrollLeftStatus=true;
        this.scrollRightStatus=true;
      }

  console.log(this.scrollLeftStatus,this.scrollRightStatus,Math.ceil(this.scrollContainer.scrollLeft)-this.scrollContainer.scrollWidth-this.scrollContainer.clientWidth);
},
    scrollLeft() {
      if (true) {
        this.scrollContainer.scrollLeft -= this.scrollStep;
        this.changeColor();
      }
    },
    scrollRight() {
      if (this.canScrollRight) {
        this.scrollContainer.scrollLeft += this.scrollStep;
        this.changeColor();
      }
    },
  },
};
</script>

<style scoped>
.scrollable-container {
  position: relative;
  width: 100%;
  overflow: hidden;
  height: max-content;
  position: relative;
}

.scrollable-row {
  white-space: nowrap;
  overflow-x: hidden;
  transition: scroll-left 0.2s ease;
  scroll-behavior: smooth;
}

.row {
  display: flex;
  margin-bottom: 20px;
  flex-flow: row nowrap;
  padding-left: 30px;
}

.col-2 {
  flex: 0 0 16.66667%;
  max-width: 16.66667%;
  margin-right: 20px;
}

.scroll-button {
  position: absolute;
  z-index: 1;
  background-color: #ef9e3f;
  border: none;
  padding: 10px;
  cursor: pointer;
  font-size: 20px;
  transition: 0.3s;
  border-radius: 5px;
  top: 30%;
}

.left-button {
  left: 0px;
}

.right-button {
  right: 0px;
}

.scroll-button:hover {
  background-color: #ccc;
}
</style>
