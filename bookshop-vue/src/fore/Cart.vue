<template>
    <div>
        <el-row style="margin-left: 20%;margin-right: 20%;margin-top: 30px">
            <h3>购物车</h3>
            <el-table :data="orderItems"
                      border>
                <el-table-column prop="product.name"
                                 label="商品信息"/>
                <el-table-column prop="product.price"
                                 label="单价"/>
                <el-table-column label="数量">
                    <el-input-number v-model="num" :min="1"></el-input-number>
                </el-table-column>

            </el-table>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "Cart",
        data(){
            return{
                uri:'cart',
                orderItems:[],
            }
        },
        created() {
            this.axios.get(this.uri)
                .then(response => {
                        this.orderItems = response.data;

                    }
                )
        }
    }
</script>

<style scoped>

</style>