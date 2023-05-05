<template>
  <v-container
    class="align-center justify-center"
  >
    <Workflow :title="workflowTitle" :steps="workflowSteps">
      <template #workflow-slot>

      </template>
    </Workflow>

    <v-card flat class="align-center justify-center">
      <v-img src="@/assets/ita.png" style="max-height: 200px;"/>
      <!--source: app.logo.com-->
    </v-card>
    <v-card-title class="text-pre-wrap text-center my-8"><h1>Welcome to the ITA-tournament manager.</h1></v-card-title>
    <v-card class="mx-auto align-center justify-center" max-width="1000">
      <v-toolbar color="primary">
        <v-toolbar-title class="text-center">Navigation</v-toolbar-title>
      </v-toolbar>
      <v-list v-model:opened="open" open-strategy="list" density="comfortable">
        <v-list-item to="/home" prepend-icon="mdi-home" title="Home"></v-list-item>
        <v-list-group  value="Project Management">
          <!--<template v-slot:activator="{props}">
            <v-list-item
              v-bind="props"
              prepend-icon="mdi-database"
              title="Project Management"
            ></v-list-item>
          </template>
          <v-list-item to="/ProjectManagement" link prepend-icon="mdi-database-eye">View projects</v-list-item>
          <v-list-item to="/project/new" link prepend-icon="mdi-database-plus">Create project</v-list-item>
          <v-list-item to="/ProjectManagement/new/location" link prepend-icon="mdi-database-plus">Create location</v-list-item>
          <v-list-item to="/ProjectManagement/new/section" link prepend-icon="mdi-database-plus">Create section</v-list-item>
          <v-list-item to="/ProjectManagement/new/measurement" link prepend-icon="mdi-database-plus">Create measurement</v-list-item>-->
        </v-list-group>
        <v-list-group value="Data analysis">
          <!--<template v-slot:activator="{props}">
            <v-list-item
              v-bind="props"
              prepend-icon="mdi-finance"
              title="Data analysis"
            ></v-list-item>
          </template>-->
        </v-list-group>
        <v-list-group value="User management" v-if="authStore.userInfo?.roles.includes('Researcher') || authStore.userInfo?.roles.includes('Admin')">
          <!--<template v-slot:activator="{props}">
            <v-list-item
              v-bind="props"
              prepend-icon="mdi-account-group"
              title="User management"
            ></v-list-item>
          </template>
          <v-list-item to="/users" link prepend-icon="mdi-account-group">Show users</v-list-item>
          <v-list-item to="/users/new" link prepend-icon="mdi-account-plus">Create user</v-list-item>-->
        </v-list-group>
      </v-list>
    </v-card>
  </v-container>


</template>

<script lang="ts" setup>
const workflowTitle= 'Workflow'
const workflowSteps= [
  {
    id: 1,
    name: 'Step 1',
    completed: true,
  },
  {
    id: 2,
    name: 'Step 2',
    completed: true,
  },
  {
    id: 3,
    name: 'Step 3',
    completed: false,
  },
  {
    id: 4,
    name: 'Step 4',
    completed: false,
  },
]


import {useThemeStore} from "@/stores/ThemeStore";
import {ref} from "vue";
import {useAuthStore} from "@/stores/AuthStore";
import Workflow from "@/components/WorkflowProgress.vue";
const authStore = useAuthStore();
useThemeStore() //load theme;

const open=ref<string[]>();
</script>
