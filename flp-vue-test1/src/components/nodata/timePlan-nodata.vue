<template>
  <div class="zwsj">
    <img :src="icon" :width="imgW" :height="imgH"/>
    <div style="color: #999;font-size: 12px;margin-top: 5px">{{text}}</div>
  </div>
</template>

<script>
  export default {
    name: "timePlan-nodata",
    props: {
      icon: {
        require: true
      },
      text: {
        require: true
      },
      imgW: {
        default: "320px"
      },
      imgH: {
        default: "200px"
      }
    }
  }
</script>

<style scoped>
  .zwsj {
    padding: 120px 0;
    text-align: center;
  }
</style>

