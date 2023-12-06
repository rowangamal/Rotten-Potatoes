<template>
  <div class="scrollable-row">
    <div class="row flex-nowrap">
      <actorCard
        class="col-2"
        v-for="ac in actorss"
        :key="ac"
        :name="ac.name"
        :character="ac.character"
        :image="ac.profile_path"
      />
    </div>
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
      actorss :[],
      done:false
    };
  },
  updated(){
  if(storeID.currMov && !this.done){
    this.actorss=storeID.currMov;
    console.log(this.actorss);
    this.done=true;
  }
  },
  methods: {
    changeStateMovie() {
      this.$emit("changeStateMovie", "MoviePage", this.movie_id);
    },
  },
};
</script>

<style scoped>
.scrollable-row {
  overflow-x: auto;
  white-space: nowrap;
}

.row {
  margin-top: 20px;
}

.card {
  width: 200px;
  margin-right: 20px;
  position: relative;
  border: solid 2px transparent;
  box-shadow: 2px 2px 5px -5px #000000;
}

.scrollable-row {
  margin-left: 20px;
}
</style>