<template>
  <div class="app">
    <leftMenuGSES />
    <div id="GSEShome">
      <div>
        <h1>Welcome</h1>
      </div>
    </div>
  </div>
</template>

<script>
import leftMenuGSES from './GSESLeftMenu.vue';
export default {
  components: { leftMenuGSES },
  name: 'GSESHome',
  data() {
    return {};
  }
}
</script>

<style scoped>
#GSEShome {
  width: 500px;
  border: 1px solid #CCCCCC;
  background-color: #FFFFFF;
  margin-top: 10px;
  margin-left: auto;
  padding: 0px;
}
</style>le>