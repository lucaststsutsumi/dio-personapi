package one.innovation.personapi.service;

import one.innovation.personapi.MessageResponseDTO;
import one.innovation.personapi.dto.request.PersonDTO;
import one.innovation.personapi.entity.Person;
import one.innovation.personapi.mapper.PersonMapper;
import one.innovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private PersonMapper personMapper = PersonMapper.INSTANCE;

    // put the @Autowired annotation on the constructor facilitates when the developer has to do the tests
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        final var personToSave = personMapper.toModel(personDTO);
        final Person savedPerson = personRepository.save(personToSave);

        return MessageResponseDTO
                .builder()
                .message("created person with ID: " + savedPerson.getId())
                .build();
    }
}
