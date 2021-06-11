<template>
  <div class="app">
    <left-menu-advisor/>

    <div id="advisorhome">
      <div>
        <h1>Welcome</h1>
      </div>

    </div>
  </div>
</template>

<script>
import LeftMenuAdvisor from './AdvisorLeftMenu.vue';

export default {
  components: {LeftMenuAdvisor},
  name: 'AdvisorHome',
  data() {
    return {};
  }
}
</script>

<style scoped>
advisorhome {
  width: 500px;
  border: 1px solid #CCCCCC;
  background-color: #FFFFFF;
  margin-top: 10px;
  margin-left: auto;
  padding: 0;
}
</style>