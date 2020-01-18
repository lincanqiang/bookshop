<template>
    <div>
        <div>
            <el-carousel height="600px"
                        indicator-position="outside">
             <el-carousel-item v-for="item in pics" :key="item.url">
                    <img :src="item.url"/>
                </el-carousel-item>
            </el-carousel>
        </div>
        <div>
            <nav-bar/>
        </div>
    </div>
</template>

<script>
    import NavBar from "./NavBar";
    export default {
        name: "Home",
        data(){
            return{
                pics: [
                    { url: require("../assets/carousel-1.jpg") },
                    { url: require("../assets/carousel-2.jpg") },
                    { url: require("../assets/carousel-3.jpg") },
                    { url: require("../assets/carousel-4.jpg") },
                    { url: require("../assets/carousel-5.jpg") }
                ]
            }
        },
        methods: {
            },
        components:{NavBar}
    }
</script>

<style scoped>

</style>