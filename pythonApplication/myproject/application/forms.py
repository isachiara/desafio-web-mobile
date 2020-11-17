from django import forms
from .models import Person


class PersonForm(forms.ModelForm):
    name = forms.CharField(label='Nome')
    lastname = forms.CharField(label='Sobrenome')
    email = forms.CharField(label='E-mail')
    password = forms.CharField(widget=forms.PasswordInput(), label='Senha')
    class Meta:
        model = Person
        fields = ['name', 'lastname', 'email', 'password']
