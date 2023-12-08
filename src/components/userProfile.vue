<template>
    <section class="content">
        <div class="left-content">
            <ul class="info">
                <li>User info</li>
                <li>Favorites</li>
                <li>Watch list</li>
                <li>Settings</li>
            </ul>
        </div>
        <div class="right-content">
            <div class="image-container">
                <img v-if="this.user.gender==='male'" class="user-image" src="../assets/MrPotato.webp" alt="../assets/MrPotato2.webp">
                <img v-else class="user-image"  src="../assets/MRSPOTATO.webp" alt="../assets/mrsPotato2.jpg">
            </div>
            <div class="user-data">
                <div class="email">
                    <label style="align-content: start;">Email</label>
                    <div style="min-width:100%;" class="data">{{this.user.email}}</div>
                </div>
                <div class="names">
                    <div>
                        <label>First name</label>
                        <div class="data">{{this.user.firstName}}</div>
                    </div>
                        <div>
                            <label>Last name</label>
                            <div class="data">{{ this.user.lastName }}</div>
                        </div>
                        <div>
                            <label>Username</label>
                            <div class="data">{{this.user.userName}}</div>
                        </div>
                        <div>
                            <label>Gender</label>
                            <div class="data">{{this.user.gender}}</div>
                        </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
export default {
    props: ["user"],
    data(){
        return{
        }
    },
}
</script>

<style scoped>
.content {
    display: flex;
    height: 100%;
    width: 100%;
}
.left-content {
    background-color: #000;
    display: flex;
    width: 25%;
    height: 720px;
    flex-direction: column;
    justify-content: space-around;
}
.info{
    display: flex;
    flex-direction: column;
    justify-items: space-between;
}
.info li{
    list-style: none;
    color: #EEEE;
    font-size:30px ;
    margin: 30px 10px;
    cursor: pointer;
}
.right-content{
    display: block;
    width: 75%;
    height: 720px;
    background-color: #eee;
}
.image-container{
    margin: 60px 60px 0px 70px;
    width: 170px; 
    height: 170px; 
    overflow: hidden;
    border-radius: 50%; 
    border: 2px solid #333;
}
.user-image{
    width: 100%; 
    height: 100%; 
    border-radius: 50%;
    object-fit: contain;
}
.user-data{
    display: flex;
    flex-direction: column;
    margin: 20px;
    padding: 15px;
    align-items: center;
    width: 600px;
    font-size: 30px;
}
.names{
    display: flex;
    padding: 25px;
    margin: 20px;
    flex-wrap: wrap;
    gap: 5px 80px ;
}
.email{
    justify-items: flex-start;
    justify-content: flex-start;
}
.line{
    display: flex;
    flex-direction: row;
    justify-content: space-around;
}
.data{
    background-color: rgb(202, 202, 194);
    min-width: 200px;
    max-width: max-content;
}
</style>