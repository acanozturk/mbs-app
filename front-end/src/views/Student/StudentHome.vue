<template>
  <div class="app">
    <leftMenuStudent/>
    <div id="studenthome">
      <div>
        <h1>Welcome</h1>
      </div>
    </div>
  </div>
</template>

<script>
import leftMenuStudent from './StudentLeftMenu';
export default {
  components: {leftMenuStudent},
  name: 'StudentHome',
  data() {
    return {};
  }
}
</script>

<style scoped>
#studenthome {
  width: 500px;
  border: 1px solid #CCCCCC;
  background-color: #FFFFFF;
  margin-top: 10px;
  margin-left: auto;
  padding: 0;
}
</style>