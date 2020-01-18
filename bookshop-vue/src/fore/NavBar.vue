<template>
    <div>
        <el-row v-for="row in bean"
                :key="row.id"
                style="text-align: center;margin-left: 20%;margin-right: 20%"
                :gutter="20">
            <el-col>
                    <h3>{{row.name}}
                        <el-button type="info"
                                   icon="el-icon-arrow-right"
                                   size="mini"
                                   @click="handleMore(row.id)"/>
                    </h3>
            </el-col>
            <el-col v-for="item in row.products"
                    :key="item.id"
                    :span="6"
                    >
                <el-card :body-style="{padding:'0px'}"
                         style="width: 195px"
                         shadow="hover"
                         @click.native="handleSkip(item.id)">
                    <el-image :src="item.image"
                              style="height: 276px;width: 195px"
                    />
                    <div style="text-align: center">
                        <p style="font-size: 5px">{{item.name}}</p>
                        <p style="color: #9d9d9d;font-size: 3px">{{item.author}}</p>
                        <p style="color:#fb4e44;font-size: 5px">¥{{item.price}}</p>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "NavBar",
        data(){
            return{
                bean:[],
                products:[],
            }
        },
        created() {
            let uri = '/preview';
            // eslint-disable-next-line no-console
            console.log(`123`)
            this.axios.get(uri)
                .then(response => {
                    this.bean = response.data;
                })
        },

        methods:{
            handleMore(id){
                this.$router.push({
                    path: '/category',
                    query:{
                        id:id
                    }
                })
            },
            handleSkip(id){
                this.$router.push({
                    path: '/product',
                    query:{
                        id:id
                    }
                })
            }
    }
    }
</script>

<style scoped>

</style>