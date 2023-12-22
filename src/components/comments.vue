<template>
  <div>
    <div
      class="comment d-flex flex-column gap-4"
      v-for="comment in comments"
      :key="comments.indexOf(comment)"
    >
      <div class="name d-flex">
        <h3>{{ comment.username }}</h3>

        <div class="commentdiv d-flex align-items-baseline">
          <div class="yourrate">
            <i
              class="fa-solid fa-star"
              :style="{ color: comment.rate / 2 > 0 ? '#EF9E3F' : 'white' }"
            ></i>
            <i
              class="fa-solid fa-star"
              :style="{ color: comment.rate / 2 > 1 ? '#EF9E3F' : 'white' }"
            ></i>
            <i
              class="fa-solid fa-star"
              :style="{ color: comment.rate / 2 > 2 ? '#EF9E3F' : 'white' }"
            ></i>
            <i
              class="fa-solid fa-star"
              :style="{ color: comment.rate / 2 > 3 ? '#EF9E3F' : 'white' }"
            ></i>
            <i
              class="fa-solid fa-star"
              :style="{ color: comment.rate / 2 > 4 ? '#EF9E3F' : 'white' }"
            ></i>
          </div>
        </div>
      </div>
      <div class="comments">
        <p>{{ comment.comment }}</p>
      </div>
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
  margin: 10px;
  padding: 10px;
  border: none;
  flex-wrap: wrap;
  justify-content: left;
  align-items: baseline;
}
.comments {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: left;
  align-items: baseline;
  text-align: left;
}
.commentdiv {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: space-between;
  align-items: baseline;
}
.yourrate {
  display: flex;
  justify-content: center;
  align-items: baseline;
  width: 50%;
}
.name {
  display: flex;
  justify-content: space-between;
  align-items: baseline;
  width: 100%;
}
p{
  font-size: 16px;
}
h3{
  font-size: 30px;
}
</style>