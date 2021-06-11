<template>
  <div class="app">
    <leftMenuJury/>
    <div id="juryhome">
      <div>
        <h1>Welcome</h1>
      </div>
    </div>
  </div>
</template>

<script>
import leftMenuJury from './JuryLeftMenu';

export default {
  components: {leftMenuJury},
  name: 'JuryHome',
  data() {
    return {};
  }
}
</script>

<style scoped>
#juryhome {
  width: 500px;
  border: 1px solid #CCCCCC;
  background-color: #FFFFFF;
  margin-top: 10px;
  margin-left: auto;
  padding: 0;
}
</style>