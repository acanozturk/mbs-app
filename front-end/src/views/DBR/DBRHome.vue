<template>
  <div class="app">
    <leftMenuDBR/>
    <div id="DBRhome">
      <div>
        <h1>Welcome</h1>
      </div>

    </div>
  </div>
</template>

<script>
import leftMenuDBR from './DBRLeftMenu.vue';

export default {
  components: {leftMenuDBR},
  name: 'DBRHome',
  data() {
    return {};
  }
}
</script>

<style scoped>
#DBRhome {
  width: 500px;
  border: 1px solid #CCCCCC;
  background-color: #FFFFFF;
  margin-top: 10px;
  margin-left: auto;
  padding: 0;
}
</style>