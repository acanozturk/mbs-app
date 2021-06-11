<template>
  <div id="nav">
    <div>
      <router-link to="/student/advisor-selection" replace>Advisor Selection</router-link>
    </div>
    <div>
      <router-link to="/student/program-plan-form" replace>Program Plan Form</router-link>
    </div>
    <div>
      <router-link to="/student/thesis-management" replace>Thesis Management</router-link>
    </div>
    <div>
      <router-link to="/student/form-menu" replace>Form Menu</router-link>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  methods: {
    select: function (args) {
      this.$router.push(args.item.text);
    }
  }
};
</script>

<style>
left-menu-student {
  width: 200px;
  padding: 100px;
  overflow-x: hidden;
  overflow-y: auto;
}
</style>