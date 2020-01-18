<template>
    <el-row style="text-align: center">
        <el-col :span="12"
                :offset="6">
            <el-divider></el-divider>
            <el-tag>项目地址</el-tag>
            <el-link :underline="false"
                     href="https://github.com/lincanqiang/bookshop">Github</el-link>
            <el-tag type="info" style="margin-left: 20px">参考文档</el-tag>
            <el-link :underline="false"
                     href="https://spring.io/projects/spring-boot">Spring Boot</el-link>
            <el-divider direction="vertical"></el-divider>
            <el-link :underline="false"
                     href="https://cn.vuejs.org/index.html">Vue</el-link>
            <el-divider direction="vertical"></el-divider>
            <el-link :underline="false"
                     href="https://element.eleme.cn/#/zh-CN">Element</el-link>
        </el-col>
    </el-row>
</template>

<script>
    export default {
        name: "Footer"
    }
</script>

<style scoped>

</style>