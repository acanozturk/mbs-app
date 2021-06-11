<template>
  <div class="app">
    <leftMenuIBDR />
    <div id="IBDRhome">
      <div>
        <h1>Welcome</h1>
      </div>
    </div>
  </div>
</template>

<script>
import leftMenuIBDR from './IBDRLeftMenu.vue';
export default {
  components: { leftMenuIBDR },
  name: 'IBDRHome',
  data() {
    return {};
  }
}
</script>

<style scoped>
#IBDRhome {
  width: 500px;
  border: 1px solid #CCCCCC;
  background-color: #FFFFFF;
  margin-top: 10px;
  margin-left: auto;
  padding: 0px;
}
</style>