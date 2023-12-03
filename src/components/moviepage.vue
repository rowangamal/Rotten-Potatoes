<template>
  <div class = "container-fluid">
    <section>
        <div class="cont d-flex flex-column justify-content-end "
        style="background: ;"
        >
        <div class="movieDetails d-flex justify-content-between">
            <div class="description flex-fill">
                <h1 class="title">
                    {{ title }}  <i class="fa-solid fa-bookmark ibtn"></i>
                </h1>
                <div class="info d-flex justify-content-around">
                    <p class="rate">
                        <i class="fa-solid fa-star imark"></i>{{ rate }} 
                    </p>
                    <p class="data">
                        some data
                    </p>
                </div>
                <p class = "moviedescription">
                    {{ description }}
                </p>
                <button type="button" class="btn trailer">Play Now <i class="fa-solid fa-play"></i></button>
                <button type="button" class="btn trailer">Trailer</button>
            </div>
            <div class="artists flex-fill">
                <h1 class="actors">
                    Actors
                </h1>
                <carousel :category="cat"/>
            </div>
        </div>
        <div  v-if="isloggedin" class = "comments d-flex flex-column align-content-start" >
            <div class="commentdiv d-flex align-items-baseline">
                <h1 class="commenthead">
                    comment and review
                </h1>
                <div class="yourrate">
                    <i class="fa-solid fa-star" @mouseover="mouseOver(1)" @mousedown="mousedown(1)" @mouseleave="mouseleave"
                    :style="{ color: (usersRate > 0 )? '#EF9E3F' : 'white' }"
                    ></i>
                    <i class="fa-solid fa-star" @mouseover="mouseOver(2)" @mousedown="mousedown(2)" @mouseleave="mouseleave"
                    :style="{ color: (usersRate > 1 )? '#EF9E3F' : 'white' }"
                    ></i>
                    <i class="fa-solid fa-star" @mouseover="mouseOver(3)" @mousedown="mousedown(3)" @mouseleave="mouseleave"
                    :style="{ color: (usersRate > 2 )? '#EF9E3F' : 'white' }"
                    ></i>
                    <i class="fa-solid fa-star" @mouseover="mouseOver(4)" @mousedown="mousedown(4)" @mouseleave="mouseleave"
                    :style="{ color: (usersRate > 3 )? '#EF9E3F' : 'white' }"
                    ></i>
                    <i class="fa-solid fa-star" @mouseover="mouseOver(5)" @mousedown="mousedown(5)" @mouseleave="mouseleave"
                    :style="{ color: (usersRate > 4 )? '#EF9E3F' : 'white' }"
                    ></i>
                </div>
            </div>
            <input
                v-model="text"
                type="text"
                class="form-control thecomment"
                placeholder="comment"
            />
        </div>
    </div>
    </section>
  </div>
</template>

<script>
import carousel from "./carousel.vue"
export default {
    props: ["index"],
    components:{
        carousel,
    },
    data(){
        return{
            isloggedin:true ,
            image:"./az5ucwh1vcg71.jpg" ,
            rate: 8 ,
            usersRate : 0,
            clicked:false ,
            title: "DUNE" ,
            description: "A noble family becomes embroiled in a war for control over the galaxy's most valuable asset while its heir becomes troubled by visions of a dark future." ,
            comments:[
                { comment :'this movie is cool' ,
                user : "pola"           
                } ,
                {comment : 'what the hell have I just watched' ,
                user : "hany" 
                }
            ] ,
        }
    },
    methods:{
        mouseOver(index){
            if(!this.clicked){
                this.usersRate = index ;
            }
        } ,
        mouseleave(){
            if(!this.clicked){
                this.usersRate = 0
            } 
        },
        mousedown(index){
            this.usersRate = index ;
            this.clicked = true ;
        }
    }
}
</script>

<style scoped>
.actors{
    padding-top: 60px;
}
.movieDetails{
    padding: 50px ;
}
.description{
    max-width: 40% ;
}
.trailer{
    margin: 5px;
    width: 241px;
    height: 53px;
    flex-shrink: 0;
    background: #EF9E3F;
}
.cont{
    color: #FFF;
    background: url('./az5ucwh1vcg71.jpg') ;
    background-size: cover;
    min-height: 1000px;
    margin : 0 ;
    padding: 0;
}
.title{
    font-size: 64px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
}
.rate{
    color: #FFF;
    font-family: "Quicksand";
    font-size: 20px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
}
.data{
    color: #FFF;
    font-family: "Quicksand";
    font-size: 20px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
}
.moviedescription{
    color: #FFF;
    font-family: "Quicksand";
    font-size: 20px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
    padding: 20px;
}
.trailer:hover{
    background: #FFF;
    color : #EF9E3F;
}
.actors{
    font-size: 32px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
}
.comments{
    padding: 80px ;
}
.commenthead{
    text-align: start;
}

input[type=text] {
  height : 100px ;
  width:200px ;
  transition: width 0.4s ease-in-out;
  font-size: 35px;
  color: rgb(183, 183, 183);
  padding: 16px 32px;
}

input[type=text]:focus {
    width: 60%;
    min-height: 80px;
    font-size: 35px;
    padding: 16px 32px;
    text-decoration: none;
    cursor:text ;
    color: black;
}
.yourrate{
    font-size: 40px ;
    padding-left: 40px;
}
.ibtn:hover{
    color: #EF9E3F;
}
.prevcomments{
    margin: 4px ;
    background: white;
    color: black;
    border-radius: 5px;
    max-width: 1000px;
}
.imark{
    color: #EF9E3F;
}
</style>