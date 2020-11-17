from django.conf.urls import url
from django.urls import path
from django.contrib.auth import views as auth_views
from .views import list_people, create_person, update_person, delete_person, login_person


urlpatterns = [
    path('', list_people, name="list_people"),
    path('new', create_person, name="create_person"),
    path('update/<int:id>', update_person, name="update_person"),
    path('delete/<int:id>', delete_person, name="delete_person"),

    path('login', login_person, name="login_person"),
]
