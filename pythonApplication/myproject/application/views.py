import json

from django.http import JsonResponse
from django.shortcuts import render, redirect
from django.views.decorators.csrf import csrf_exempt

from .models import Person
from .forms import PersonForm


def create_person(request):
    form = PersonForm(request.POST or None)

    if form.is_valid():
        form.save()
        return redirect('list_people')

    return render(request, 'create-person-form.html', {'form': form})


def list_people(request):
    people = Person.objects.all()
    return render(request, 'list-people.html', {'people': people})


def update_person(request, id):
    person = Person.objects.get(id=id)
    form = PersonForm(request.POST or None, instance=person)

    if form.is_valid():
        form.save()
        return redirect('list_people')

    return render(request, 'update-person.html', {'form': form, 'person': person})


def delete_person(request, id):
    person = Person.objects.get(id=id)

    if request.method == 'POST':
        person.delete()
        return redirect('list_people')

    return render(request, 'delete-person.html', {'person': person})


@csrf_exempt
def login_person(request):
    if request.method == "POST":
        body = json.loads(request.body)
        email = body['email']
        password = body['pass']

        try:
            person = Person.objects.get(email=email)
        except:
            data = {
                'result': 'nok',
                'erro': 'Pessoa não encontrada'
            }
            return JsonResponse(data)

        if person.password == password:
            personJson = {
                'token': '',
                'name_person': person.name,
                'lastname_person': person.lastname,
                'result': 'ok'
            }
        return JsonResponse(personJson)

    data = {
        'result': 'nok',
        'erro': 'Senha Incorreta'
    }
    return JsonResponse(data)
