namespace Lab03.domain;

public class ProbeParticipant : Identifiable<int>
{
    private Participant participant;

    private Proba proba;

    private int id;

    public ProbeParticipant(Participant participant, Proba proba, int id)
    {
        this.participant = participant;
        this.proba = proba;
        this.id = id;
    }


    public void setId(int id)
    {
        throw new NotImplementedException();
    }

    public int getId()
    {
        throw new NotImplementedException();
    }

    public Participant Participant
    {
        get => participant;
        set => participant = value ?? throw new ArgumentNullException(nameof(value));
    }

    public Proba Proba
    {
        get => proba;
        set => proba = value ?? throw new ArgumentNullException(nameof(value));
    }

    public int Id
    {
        get => id;
        set => id = value;
    }
}