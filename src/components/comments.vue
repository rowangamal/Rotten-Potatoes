<template>
  <div>
    <h2>Comments</h2>
    <div
      class="comment d-flex gap-4"
      v-for="comment in comments"
      :key="comments.indexOf(comment)"
    >
      <p>{{ comment.username }}</p>
      <p>{{ comment.comment }}</p>
    </div>
  </div>
</template>

<script>
export default {
  props: ["comments"],
};
</script>

<style scoped>
.comment {
  border: 1px solid black;
  margin: 10px;
  padding: 10px;
  border-radius: 10px;
}
</style>