<template>
    <div>
        <top-bar/>
        <search-bar/>
        <el-main>
            <router-view/>
        </el-main>
        <foo-ter/>
    </div>
</template>

<script>
    import topBar from "./TopBar"
    import searchBar from "./SearchBar"
    import fooTer from "./Footer"
    export default {
        name: "Container",
        components:{ topBar,searchBar,fooTer},
    }
</script>

<style scoped>

</style>