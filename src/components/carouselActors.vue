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
    <button @click="scrollLeft" class="scroll-button left-button">left</button>
    <button @click="scrollRight" class="scroll-button right-button">right</button>
  </div>
</template>

<script>
import actorCard from "./actorCard.vue";
import { storeID } from './id';

export default {
  props: ["actor"],
  components: {
    actorCard,
  },
  data() {
    return {
      actorss: [],
      done: false,
      scrollStep: 250,
    };
  },
  computed: {
    scrollContainer() {
      return this.$refs.scrollContainer;
    },
    canScrollRight() {
      return this.scrollContainer && (this.scrollContainer.scrollLeft < (this.scrollContainer.scrollWidth - this.scrollContainer.clientWidth));
    },
  },
  updated() {
    if (storeID.currMov && !this.done) {
      this.actorss = storeID.currMov;
      console.log(this.actorss);
      this.done = true;
    }
  },
  methods: {
    scrollLeft() {
      if (true) {
        this.scrollContainer.scrollLeft -= this.scrollStep;
      }
    },
    scrollRight() {
      if (this.canScrollRight) {
        this.scrollContainer.scrollLeft += this.scrollStep;
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
}

.scrollable-row {
  white-space: nowrap;
  overflow-x: hidden;
}

.row {
  display: flex;
  margin-bottom: 20px;
  flex-flow: row nowrap;
}

.col-2 {
  flex: 0 0 16.66667%;
  max-width: 16.66667%;
  margin-right: 20px;
}

.scroll-button {
  position: absolute;
  top: 50%;
  background-color: #ddd;
  border: none;
  padding: 10px;
  cursor: pointer;
  font-size: 20px;
}

.left-button {
  left: 0;
}

.right-button {
  right: 0;
}

.scroll-button:hover {
  background-color: #ccc;
}
</style>
