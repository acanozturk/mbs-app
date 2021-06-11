<template>

  <div id="nav">
    <div>
      <router-link to="/advisor/student-approval" replace>Student Approval</router-link>
    </div>
    <div>
      <router-link to="/advisor/update-thesis-topics" replace>Update Thesis Topics</router-link>
    </div>
    <div>
      <router-link to="/advisor/complete-program-plan-forms" replace>Complete Program Plan Forms</router-link>
    </div>
    <div>
      <router-link to="/advisor/submit-thesis-copies" replace>Submit Thesis Copies</router-link>
    </div>
    <div>
      <router-link to="/advisor/jury-recommendation" replace>Jury Recommendation</router-link>
    </div>
    <div>
      <router-link to="/advisor/final-thesis-copy-deadline-extension" replace>Final Thesis Copy Deadline Extension
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  name: 'AdvisorLeftMenu',
  methods: {
    select: function (args) {
      this.$router.push(args.item.text);
    }
  }
};
</script>

<style>
left-menu-advisor {
  width: 200px;
  padding: 100px;
  overflow-x: hidden;
  overflow-y: auto;
}
</style>